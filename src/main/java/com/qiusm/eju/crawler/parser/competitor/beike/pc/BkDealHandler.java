package com.qiusm.eju.crawler.parser.competitor.beike.pc;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.bk.BkDealPc;
import com.qiusm.eju.crawler.entity.bk.BkDealUrlHistory;
import com.qiusm.eju.crawler.service.bk.IBkDealPcService;
import com.qiusm.eju.crawler.service.bk.IBkDealUrlHistoryService;
import com.qiusm.eju.crawler.utils.StreamUtils;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;

/**
 * 贝壳PC端成交数据抓取
 */
@Slf4j
@Component
public class BkDealHandler {
    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder()
            .proxyUrl("http://10.122.143.10:8890/get/ip-list/3?key=557F35CA07AE2470F80E5CFC710FE61E").charset(GBK)
            .connectTimeout(60000).readTimeout(60000)
            .retryMax(10)
            .proxyRetryTag(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)))
            .builderHttp();
    @Resource
    private IBkDealUrlHistoryService urlHistoryService;
    @Resource
    private IBkDealPcService bkDealPcService;
    private boolean isLoad = false;
    private final ThreadsUtils threadsUtils = new ThreadsUtils();
    private final int threadNum = 16;
    /**
     * 用于统计翻页，进行日志打印。
     */
    private final AtomicInteger count = new AtomicInteger(0);
    /**
     * 用于统计缓存的命中次数。
     */
    private final AtomicInteger cacheCount = new AtomicInteger(0);
    /**
     * 用于统计实际进行http请求数量
     */
    private final AtomicInteger httpCount = new AtomicInteger(0);

    @Resource
    private HashOperations<String, String, String> hashOperations;

    public void handler(String city) {
        isLoad = true;
        count.set(0);
        cacheCount.set(0);
        httpCount.set(0);
        String url = String.format("https://%s.ke.com/chengjiao/", city);
        // 通过城市获取区域列表
        List<Map<String, String>> qyList = getQyByCity(url);
        log.info("区域加载完毕，总区域数：{}", qyList.size());

        // 通过区域获取板块列表
        List<Map<String, String>> bkList = threadsUtils.executeFutures(qyList, this::getBkByQy, threadNum)
                .stream()
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        log.info("板块加载完毕：{}", bkList.size());

        // 获取板块下的成交数量
        threadsUtils.executeFutures(bkList, this::getDealCountByBk, threadNum);
        log.info("区域下成交数量加载完毕，总板块数：{}", bkList.size());

        // 板块成交中大于3000的增加条件
        bkList = countGt3000Change(bkList);
        log.info("区域下成交数量加载完毕，总板块数：{}。板块下大于3000的进行补充url。", bkList.size());

        bkList = bkList.parallelStream().filter(StreamUtils.distinctByKey(map -> map.get("url")))
                .collect(Collectors.toList());

        log.info("{}", JSONObject.toJSONString(bkList));
        // 进行分页
        List<Map<String, String>> pageRequests = createPage(bkList);
        log.info("区域分页加载完毕，总页数：{}", pageRequests.size());

        pageRequests = pageRequests.parallelStream().filter(StreamUtils.distinctByKey(map -> map.get("url")))
                .collect(Collectors.toList());
        log.info("区域分页加载完毕，总页数(去重)：{}", pageRequests.size());
        // log.info("{}", JSONObject.toJSONString(pageRequests));
        threadsUtils.executeFutures(pageRequests, this::xfRequests, threadNum);

        hashOperations.put("crawler:deal:bk", city, pageRequests.size() + "");
    }

    /**
     * 消费
     */
    private boolean xfRequests(Map<String, String> request) {
        // log.info("当前请求：{}", request);
        if (count.getAndIncrement() % 100 == 0) {
            log.info("page: {}", count.get());
        }
        String url = request.get(URL);
        if (StringUtils.contains(url, "ershoufang")) {
            return true;
        }
        String responseStr = httpGet(url);
        parserList(responseStr, request);
        return true;
    }

    /**
     * 创建翻页请求
     *
     * @param bkList 板块请求
     * @return 翻页请求
     */
    private List<Map<String, String>> createPage(List<Map<String, String>> bkList) {
        List<Map<String, String>> pageList = new ArrayList<>();

        bkList.forEach(map -> {
            map.put(PAGE, "1");
            pageList.add(map);
            int cjCount = Integer.parseInt(map.get(CJ_COUNT));
            if (cjCount > 3000) {
                log.error("当前请求下的成交数据大于3000。{}", map);
            }
            int page = cjCount / 30;
            if (page > 1) {
                String baseUrl = map.get(BASE_URL);
                if (StringUtils.isEmpty(baseUrl)) {
                    baseUrl = map.get(URL);
                }
                // 条件
                String condition = map.get(CONDITION);
                if (StringUtils.isEmpty(condition)) {
                    condition = "";
                }
                for (int i = 2; i <= page; i++) {
                    String pageUrl = String.format("%spg%s%s", baseUrl, i, condition);
                    Map<String, String> pageMap = new HashMap<>(map);
                    pageMap.put(URL, pageUrl);
                    pageMap.put(PAGE, String.valueOf(i));
                    pageList.add(pageMap);
                }
            }
        });
        return pageList;
    }

    /**
     * 通过城市获取区域
     *
     * @param url 城市url
     * @return 区域列表
     */
    private List<Map<String, String>> getQyByCity(String url) {
        String responseStr = httpGet(url);
        Document doc = Jsoup.parse(responseStr);
        Elements aList = doc.select("div.position a.CLICKDATA");
        List<Map<String, String>> qyList = new ArrayList<>();
        aList.forEach(a -> {
            if (StringUtils.contains(a.text(), "周边")) {
                return;
            }
            Map<String, String> qy = new HashMap<>();
            qy.put(URL, String.format("https://%s.ke.com%s", getCityByUrl(url), a.attr("href")));
            qy.put(CITY, getCityByUrl(url));
            qy.put(QY_NAME, a.text());
            qyList.add(qy);
        });
        return qyList;
    }

    /**
     * 通过区域获取板块
     *
     * @param qy 区域信息
     * @return 板块列表
     */
    private List<Map<String, String>> getBkByQy(Map<String, String> qy) {
        String url = qy.get(URL);
        String responseStr = httpGet(url);
        List<Map<String, String>> bkList = new ArrayList<>();
        Document doc = Jsoup.parse(responseStr);
        Elements div = doc.select("div.position div[data-role=ershoufang] div");
        if (div.size() > 1) {
            Elements aList = div.get(1).select("a");
            aList.forEach(a -> {
                Map<String, String> var = new HashMap<>();
                var.put(URL, String.format("https://%s.ke.com%s", getCityByUrl(url), a.attr("href")));
                var.put(CITY, getCityByUrl(url));
                var.put(BK_NAME, a.text());
                var.put(QY_NAME, qy.get(QY_NAME));
                bkList.add(var);
            });
        } else {
            // 当前区域下，没有板块
            Map<String, String> var = new HashMap<>();
            var.put(URL, qy.get(URL));
            var.put(CITY, getCityByUrl(url));
            var.put(BK_NAME, qy.get(QY_NAME));
            var.put(QY_NAME, qy.get(QY_NAME));
            bkList.add(var);
        }
        return bkList;
    }

    /**
     * 获取当前板块下，成交数据的数量
     *
     * @param bk 板块信息
     */
    private String getDealCountByBk(Map<String, String> bk) {
        String responseStr = httpGet(bk.get(URL));
        Document doc = Jsoup.parse(responseStr);
        Element span = doc.selectFirst("div.resultDes span");
        bk.put(CJ_COUNT, span.text());
        return span.text();
    }

    private List<Map<String, String>> countGt3000Change(List<Map<String, String>> bkList) {
        // 筛选大于3000的板块列表， 对大于三千的，需要进一步细化。
        List<Map<String, String>> bk3kList = bkList.stream()
                .filter(map -> Integer.parseInt(map.get(CJ_COUNT)) > 3000)
                .collect(Collectors.toList());

        // 筛选小于3000的板块列表
        List<Map<String, String>> bkn3kList = bkList.stream()
                .filter(map -> Integer.parseInt(map.get(CJ_COUNT)) < 3000)
                .collect(Collectors.toList());
        // 将大于3000的板块进行切割，按价格切割,并进行请求
        List<Map<String, String>> bkn3kList2 = new ArrayList<>();
        bk3kList.forEach(map -> {
            for (int i = 1; i <= 6; i++) {
                Map<String, String> map1 = new HashMap<>(map);
                String condition = String.format("p%s", i);
                map1.put(URL, map.get("url") + condition);
                // 用于后续分页方便组装请求
                map1.put(BASE_URL, map.get("url"));
                map1.put(CONDITION, condition);
                bkn3kList2.add(new HashMap<>(map1));
            }
        });

        threadsUtils.executeFutures(bkn3kList2, this::getDealCountByBk, threadNum);

        bkn3kList.addAll(bkn3kList2);
        bkList.clear();
        return bkn3kList;
    }

    private void parserList(String responseStr, Map<String, String> request) {
        Document doc = Jsoup.parse(responseStr);
        Elements liEle = doc.select("ul.listContent>li");
        if (liEle == null) {
            log.info("无成交数据：{}", request);
            return;
        }
        liEle.forEach(li -> {
            Element a = li.child(0);
            Element div = li.child(1);
            // 成交详情页面URL
            String detailUrl = a.absUrl("href");
            String layoutImg = "";
            Element img = a.selectFirst("img");
            if (img != null) {
                layoutImg = img.attr("src");
            }

            String title = "";
            String houseInfo = "";
            String dealDate = "";
            String totalPrice = "";
            String positionInfo = "";
            String unitPrice = "";
            String listedPrice = "";
            String dealHouseTxt = "";

            try {
                // 成交标题
                title = div.selectFirst("div.title").text();

                Element address = div.selectFirst("div.address");
                // 朝向、装修状况
                houseInfo = address.selectFirst("div.houseInfo").text();
                // 成交日期
                dealDate = address.selectFirst("div.dealDate").text();
                // 总价
                totalPrice = address.selectFirst("div.totalPrice").text();

                Element flood = div.selectFirst("div.flood");
                // 房屋位置描述
                positionInfo = flood.selectFirst("div.positionInfo").text();
                // 均价
                unitPrice = flood.selectFirst("div.unitPrice").text();

                Element dealCycleInfo = div.selectFirst("div.dealCycleeInfo");
                Element dealCycleTxt = dealCycleInfo.selectFirst("span.dealCycleTxt");
                // 挂牌价格
                listedPrice = dealCycleTxt.text();
                Element dealHouseInfo = div.selectFirst("div.dealHouseInfo");
                // 房屋成交信息。例如：房屋满两年。
                if (dealHouseInfo != null) {
                    dealHouseInfo.text();
                }
            } catch (Exception e) {
                log.info("{},{}", request, e.getMessage());
            }

            // true JSONObject 内部结构将会用LinkedHashMap实现
            BkDealPc deal = new BkDealPc();
            deal.setCity(request.get(CITY));
            deal.setQyName(request.get(QY_NAME));
            deal.setBkName(request.get(BK_NAME));

            deal.setTitle(title);
            deal.setHouseInfo(houseInfo);
            deal.setDealDate(dealDate);
            deal.setTotalPrice(totalPrice);
            deal.setPositionInfo(positionInfo);
            deal.setUnitPrice(unitPrice);
            deal.setListedPrice(listedPrice);
            deal.setDealHouseTxt(dealHouseTxt);
            deal.setDetailUrl(detailUrl);
            deal.setLayoutImg(layoutImg);
            bkDealPcService.checkInsert(deal);
            // FileUtils.printFile(JSONObject.toJSONString(deal), "source/bk/data", "deal_pc.txt", true);
        });
    }

    private String httpGet(String url) {
        String html = null;
        if (!isLoad) {
            html = httpUtils.proxyGet(url);
            return html;
        }
        BkDealUrlHistory his = urlHistoryService.getBkHistoryByUrl(url);

        if (his != null) {
            html = his.getResult();
        } else {
            his = new BkDealUrlHistory();
        }


        if (!viewCheck(html)) {
            long s = System.currentTimeMillis();
            Map<String, String> head = new HashMap<>();
            html = httpUtils.proxyGet(url, UTF8, head);
            long e = System.currentTimeMillis();


            his.setResult(html);
            his.setUrl(url);
            his.setClassHandler("");
            his.setCity(getCityByUrl(url));
            his.setTimeConsuming(e - s);
            his.setIsSuccess(viewCheck(html) ? 1 : 0);
            urlHistoryService.upHis(his);
            if (httpCount.getAndIncrement() % 100 == 0) {
                log.info("目前http请求：{}", httpCount.get());
            }
        } else {
            if (cacheCount.getAndIncrement() % 100 == 0) {
                log.info("目前命中缓存：{}", cacheCount.get());
            }
        }
        return html;
    }

    private static String getCityByUrl(String url) {
// https://tj.ke.com/chengjiao/
        int start = "https://".length();
        int end = url.indexOf(".");
        return StringUtils.substring(url, start, end);
    }

    private boolean viewCheck(String html) {
        return !(StringUtils.isBlank(html)
                || html.startsWith("ejuResponseCode")
                || html.startsWith("ResponseError")
                || html.startsWith("ResponseCode"));
    }

    private synchronized static OkHttpUtils getHttpUtils() {
        return OkHttpUtils.Builder()
                .proxyUrl("http://10.122.143.10:8890/get/ip-list/3?key=557F35CA07AE2470F80E5CFC710FE61E").charset(UTF8)
                .connectTimeout(60000).readTimeout(60000)
                .retryMax(10)
                .proxyRetryTag(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)))
                .builderHttp();
    }

    private static final String URL = "url";
    private static final String BASE_URL = "base_url";
    private static final String CITY = "city";
    /**
     * 板块名称
     */
    private static final String BK_NAME = "bkName";
    /**
     * 区域名称
     */
    private static final String QY_NAME = "qyName";
    /**
     * 当前板块下的成交数量
     */
    private static final String CJ_COUNT = "cj_count";
    private static final String PAGE = "page";
    private static final String CONDITION = "condition";
}
