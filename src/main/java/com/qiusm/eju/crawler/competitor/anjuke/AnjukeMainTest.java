package com.qiusm.eju.crawler.competitor.anjuke;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.constant.EjuConstant;
import com.qiusm.eju.crawler.constant.SymbolicConstant;
import com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant;
import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import com.qiusm.eju.crawler.utils.lang.FileUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import com.qiusm.eju.crawler.utils.http.Download;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.qiusm.eju.crawler.utils.http.SeleniumDownload;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.*;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.*;
import static com.qiusm.eju.crawler.constant.NumberConstant.*;

/**
 * 安居客挂牌信息测试
 *
 * @author qiushengming
 */
@Slf4j
public class AnjukeMainTest {
    static final OkHttpUtils HTTP_CLIENT = CommonUtils.createHttpClient();
    static final Download DOWNLOAD = SeleniumDownload.Builder().proxyUrl(EjuConstant.PROXY_URL1).builder();
    static final Set<String> PLATE_SET = new HashSet<>();

    /**
     * 无法访问此网站
     *
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        while (i++ < 10) {
            DOWNLOAD.proxyGet("https://shanghai.anjuke.com/sale/", heads());
        }
        DOWNLOAD.quit();
    }

    public static void countPage() {
        String fileName = "./source/anjuke/anjuke_01.txt";
        String fileContent = FileUtils.readFile(new File(fileName));
        if (fileContent == null) {
            return;
        }

        Integer num = 0;
        for (String var : fileContent.split("\r\n")) {
            if (!StringUtils.contains(var, ":")) {
                continue;
            }
            for (String var1 : var.split(":")[1].split(SymbolicConstant.COMMA)) {
                if (StringUtils.equals(var1, "1")) {
                    continue;
                }
                num += Integer.parseInt(var1);
            }
        }
        System.out.println(num);
    }

    public static void anjukeStart() {
        String fileName = "./source/anjuke/anjuke_01.txt";
        String fileContent = FileUtils.readFile(new File(fileName));
        if (fileContent != null) {
            for (String var : fileContent.split("\r\n")) {
                PLATE_SET.add(var.split(":")[0]);
            }
        }
        regionListParser();
    }

    /**
     * 安居客挂牌信息 <br>
     * 第一步：在首页解析出区域列表 <br>
     * 参考URL：https://shanghai.anjuke.com/sale/ <br>
     */
    static void regionListParser() {
        String url = "https://shanghai.anjuke.com/sale/";
        String htmlStr = httpGetBody(url);
//        String htmlStr = FileUtils.readFile(new File("D:\\User\\qiushengming\\EJU\\81_anjuke\\v2021.7.5\\v2021.7.5_界面html代码.html"));
        if (StringUtils.isBlank(htmlStr)) {
            return;
        }

        Document document = Jsoup.parse(htmlStr);
        Map<String, String> data = new LinkedHashMap<>(1);
        List<Map<String, String>> list = new ArrayList<>();

        // 获取城市名称
        String city = null;
        Elements cityTag = document.select("span.city-name");
        if (cityTag.size() > 0) {
            city = cityTag.text();
        }
        data.put(CITY, city);

        // 获取筛选条件中的区域条件
        Elements aTags = document.select("section.filter-content>ul.region-line2 a");
        aTags.forEach(a -> {
            String regionHref = a1(a.attr("href"));
            String region = a.text();

            if (StringUtils.equals(region, "不限")) {
                return;
            }

            Map<String, String> map = new LinkedHashMap<>(data);
            map.put(REGION, region);
            map.put(REGION_URL, regionHref);

            list.add(map);
        });
        list.forEach(o -> {
            String htmlStr1 = httpGetBody(o.get(REGION_URL));
            System.out.printf("%s", o.get(REGION));
            plateListParser(htmlStr1);
        });
        JSONArray jsonArray = new JSONArray(Collections.singletonList(list));
        System.out.println(jsonArray.toJSONString());
    }

    /**
     * 第二步：在区域中解析板块列表 <br>
     * 参考URL:https://shanghai.anjuke.com/sale/pudong/ <br>
     */
    static void plateListParser(String htmlStr) {
        // String htmlStr = FileUtils.readFile(new File("D:\\User\\qiushengming\\EJU\\81_anjuke\\v2021.7.5\\v2021.7.5_plate_界面html代码.html"));
        if (StringUtils.isBlank(htmlStr)) {
            return;
        }

        Document document = Jsoup.parse(htmlStr);
        List<Map<String, String>> list = new ArrayList<>();

        Elements aTags = document.select("section.filter-content>ul.region-line3 a");

        aTags.forEach(a -> {
            String plateUrl = a1(a.attr("href"));
            String plate = a.text();

            if (StringUtils.equals(plate, "不限")) {
                return;
            }

            Map<String, String> map = new LinkedHashMap<>(8);
            map.put(PLATE, plate);
            map.put(PLATE_URL, plateUrl);
            list.add(map);
        });
        String prices = "0_100,100_200,200_300,300_400,400_500,500_600,600_700,700_800,800_900,900_1000,1000_1100,1100_1200,1200_1600,1600_2000,2000_";
        // 1.https://shanghai.anjuke.com/sale/pudong-q-beicai/
        // 2.https://shanghai.anjuke.com/sale/pudong-q-beicai/p50/?prices=400_500
        // 链接1要转换成链接2
        // 增加优化逻辑，可以先不带查询条件进行查询，查看当前板块是否超50页了，如果超50页了，则增加请求参数。
        list.forEach(map -> {
            if (PLATE_SET.contains(map.get(PLATE))) {
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(map.get(PLATE)).append(":");

            String htmlStr1 = httpGetBody(map.get(PLATE_URL));
            Document doc1 = Jsoup.parse(htmlStr1);
            String lastPageNum = getLastPage(doc1);
            if (!StringUtils.equals(lastPageNum, "50")) {
                sb.append(lastPageNum);
            } else {
                for (String var : prices.split(SymbolicConstant.COMMA)) {
                    String url = map.get(PLATE_URL) + "p50/?prices=" + var;
                    htmlStr1 = httpGetBody(url);
                    doc1 = Jsoup.parse(htmlStr1);
                    sb.append(getLastPage(doc1)).append(SymbolicConstant.COMMA);
                }
            }
            log.info(sb.toString());
        });
    }

    static String getLastPage(Document doc) {
        Elements lastPage = doc.select("section.pagination-wrap li.last");
        if (lastPage.size() > NUM0) {
            return lastPage.text();
        } else {
            return "1";
        }
    }

    /**
     * 如果url中带有参数则截取掉
     *
     * @param href url
     * @return url
     */
    private static String a1(String href) {
        String[] split = href.split("\\?");
        if (split.length == 2) {
            return split[0];
        }
        return href;
    }

    /**
     * 第三步：楼盘列表解析 <br>
     */
    static void houseListParser(String htmlStr) {
        // String htmlStr = FileUtils.readFile(new File("D:\\User\\qiushengming\\EJU\\81_anjuke\\v2021.7.5\\v2021.7.5_plate_界面html代码.html"));
        if (StringUtils.isBlank(htmlStr)) {
            return;
        }

        Document document = Jsoup.parse(htmlStr);
        List<Map<String, String>> list = new ArrayList<>();

        // 房屋挂牌列表<a>标签
        Elements houseTagList = document.select("section.list>div>a");
        houseTagList.forEach(a -> {
            Map<String, String> map = new LinkedHashMap<>(16);
            //缩略图
            Elements imgs = a.select("a>div.property-image>img.lazy-img");
            imgs.forEach(img -> {
                // 获取图片原始地址
                String surfacePlot = img.attr("data-src");
                map.put(CommunityRackRateConstant.SURFACE_PLOT, surfacePlot);

                // 图片上传转换
            });

            // 房屋挂牌标题
            Elements titleH3Tag = a.select("h3.property-content-title-name");
            String goodsName = titleH3Tag.attr("title");
            // 房屋详情URL
            String currentUrl = a.attr("href");
            if (StringUtils.isNotBlank(currentUrl)) {
                String[] split = currentUrl.split("\\?");
                if (split.length == 2) {
                    currentUrl = split[0];
                }
                map.put(CURRENT_RACK_RATE_URL, currentUrl);
                map.put(GOODS_NAME, goodsName);
            }

            // 获取p标签列表
            Elements houseTagInfo = a.select("div.property-content-info>p");

            // 户型、面积、朝向、楼层、建造年代
            map.put(RACK_RATE_LAYOUT, houseTagInfo.get(0).text());
            map.put(RACK_RATE_AREA, houseTagInfo.get(1).text());
            map.put(ORIENTATION, houseTagInfo.get(2).text());
            map.put(FLOOR_HEIGHT, houseTagInfo.get(3).text());
            map.put(RACK_RATE_BUILD_YEAR, houseTagInfo.get(4).text());

            // 小区名称
            map.put(TITLE, a.select("p.property-content-info-comm-name").text());
            // 小区地址
            map.put(ADDRESS, a.select("p.property-content-info-comm-address").text());
            // 小区标签
            map.put(TAG, a.select("span.property-content-info-tag").text());
            // 总价
            map.put(TOTAL_PRICE, a.select("p.property-price-total").text());
            // 均价
            map.put(AVG_PRICE, a.select("p.property-price-average").text());

            list.add(map);
        });

        // 获取下一页链接
        Elements nextActive = document.select("section.pagination-wrap a.next-active");
        if (nextActive.size() > NUM0) {
            String nextHref = nextActive.attr("href");
            if (StringUtils.isNotBlank(nextHref)) {
                System.out.println(nextHref);
            }
        }

        JSONArray jsonArray = new JSONArray(Collections.singletonList(list));
        System.out.println(jsonArray.toJSONString());
    }

    /**
     * 第四步：解析挂牌房屋详情信息 <br>
     */
    static void houseDetailParser() {
        String htmlStr = FileUtils.readFile(new File("D:\\User\\qiushengming\\EJU\\81_anjuke\\v2021.7.5\\v2021.7.5_house_detail_界面html代码.html"));
        if (StringUtils.isBlank(htmlStr)) {
            return;
        }

        Document document = Jsoup.parse(htmlStr);

        Map<String, Object> map = new LinkedHashMap<>(NUM16);
        map.put(RACK_RATE_DETAIL_URL, "");

        // 解析房源信息
        Elements houseInfo = document.getElementById("houseInfo").select("td");
        houseInfo.forEach(td -> {
            Elements span = td.select("span");
            if (span.size() != NUM2) {
                return;
            }
            String key = span.get(0).text();
            String value = span.get(1).text();
            switch (key) {
                case "产权性质":
                    map.put(RACK_RATE_TRADING_RIGHTS, value);
                    break;
                case "物业类型":
                    map.put(RACK_RATE_PROPERTY_TYPE, value);
                    break;
                case "产权年限":
                    map.put(RACK_RATE_PROPERTY_YEAR, value);
                    break;
                case "房本年限":
                    map.put(HOUSING_YEARS, value);
                    break;
                case "唯一住房":
                    map.put(SOLE_HOUSING, value);
                    break;
                case "参考预算":
                    map.put(DOWN_PAYMENT, value);
                    break;
                case "发布公司":
                    map.put(AGENT_COMPANY, value);
                    break;
                case "发布时间":
                    map.put(GOODS_PUBTIME, value);
                    break;
                case "营业执照":
                    break;
                case "房源核验编码":
                    break;
                default:
                    System.out.println(span.text());
                    break;

            }
        });

        // 房屋编码
        String houseCode = document.select("div.props-main>div.banner>div.banner-title>div.banner-title-code").text();
        map.put(GOODS_CODE, houseCode.replace("房屋编码：", ""));
        // 户型图右侧信息解析
        Elements div = document.select("div.maininfo");
        // 价格信息 单价、总价
        Elements price = div.select("div.maininfo-price");
        map.put(AVG_PRICE, price.select("div.maininfo-avgprice-price").text());
        map.put(TOTAL_PRICE, price.select("div.maininfo-price-wrap").text());
        // 户型信息 户型、楼层、面积、朝向
        Elements model = div.select("div.maininfo-model");
        // 户型
        map.put(RACK_RATE_LAYOUT, model.select("div.maininfo-model-item-1>div.maininfo-model-strong").text());
        // 所在楼层
        map.put(FLOOR_HEIGHT, model.select("div.maininfo-model-item-1>div.maininfo-model-weak").text());
        // 面积
        map.put(RACK_RATE_AREA, model.select("div.maininfo-model-item-2>div.maininfo-model-strong").text());
        // 装修
        map.put(DECORATION, model.select("div.maininfo-model-item-2>div.maininfo-model-weak").text());
        // 朝向
        map.put(ORIENTATION, model.select("div.maininfo-model-item-3>div.maininfo-model-strong").text());

        // 元信息 标签、小区信息、所属小区、所属区域\板块、小区URL
        Elements meta = div.select("div.maininfo-meta");
        // a标签
        Elements anchorWeak = meta.select("div.maininfo-community-item a.anchor-weak");
        // 小区信息
        Element community = anchorWeak.get(0);
        String communityUrl = community.attr("href");
        map.put(RACK_RATE_TITLE, community.text());
        map.put(RACK_RATE_TITLE_ID, communityUrl.substring(communityUrl.lastIndexOf("/") + 1));
        map.put(TITLE_URL, communityUrl);
        // 区域 板块
        map.put(RACK_RATE_REGION, anchorWeak.get(1).text());
        map.put(RACK_RATE_PLATE, anchorWeak.get(2).text());

        // 经纪人信息
        Elements broker = div.select("div.maininfo-broker");

        // 描述
        Elements divs = document.select("div.houseIntro-content-wrap div.houseIntro-content-item");
        divs.forEach(var -> {
            String title = var.select("div.houseIntro-content-title").text();
            String desc = var.select("div.houseIntro-content-p").text();
            switch (title) {
                case "核心卖点":
                    map.put(SELLING_POINT, desc);
                    break;
                case "业主心态":
                    map.put(PROPRIETOR_MENTALITY, desc);
                    break;
                case "服务介绍":
                    map.put(SERVICE_TAG, title);
                    map.put(SERVICE_INTRODUCTION, desc);
                    break;
                default:
                    break;
            }
        });

        // 房屋核验编码、发布时间\所属小区
        document.select("span.houseInfo-main-item-name");

        // 小区详情
        Elements communityDetailInfo = document.getElementById("community").select("div.community-info-td");
        communityDetailInfo.forEach(var -> {
            Elements pTitle = var.getElementsByClass("community-info-td-title");
            Elements pValue = var.getElementsByClass("community-info-td-value");
            String key = pTitle.text();
            String value = pValue.text();
            switch (key) {
                case "小区均价":
                    map.put(COMMUNITY_AVERAGE_PRICE, var.select("span.monthchange-money").text());
                    // 环比
                    map.put(COMMUNITY_CHAIN_RATIO, var.select("span.monthchange-value").text());
                    break;
                case "小区户数":
                    map.put(COMMUNITY_HOME_TOTAL, value);
                    break;
                case "容积率":
                    map.put(COMMUNITY_PLOT_RATE, value);
                    break;
                case "绿化率":
                    map.put(COMMUNITY_GREEN_RATE, value);
                    break;
                case "物业费用":
                    map.put(COMMUNITY_PROPERTY_PRICE, value);
                    break;
                default:
                    break;
            }
        });

        // 图片解析
        houseImageParser(document);

        System.out.println(new JSONObject(map));
    }

    static void houseImageParser(Document document) {
        //存放到图片的kafka中
        List<Map<String, String>> imgUrl = new ArrayList<>();
        // List<PictureKey> picKeylist = new ArrayList<>();
        // 第0\1是VR和视频图片，第2是户型图，后面都是室内图
        Elements imgs = document.select("div.props-left>section.gallery>div.gallery-indicator>div.gallery-indicator-image-wrap div.slick-track img");

        // 户型图
        Element layoutImg = imgs.get(2);
        Map<String, String> layoutImgMap = new LinkedHashMap<>(NUM4);
        //上传图片
        layoutImgMap.put("pic_src", layoutImg.attr("src"));
        layoutImgMap.put("pic_src_local", "");
        layoutImgMap.put("pic_key", "");
        layoutImgMap.put("pic_type", "户型图");
        imgUrl.add(layoutImgMap);

        //室内图
        for (int i = 3; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            Map<String, String> varMap = new LinkedHashMap<>(NUM4);
            varMap.put("pic_src", img.attr("src"));
            varMap.put("pic_src_local", "");
            varMap.put("pic_key", "");
            varMap.put("pic_type", "室内图");
            imgUrl.add(varMap);
        }

        JSONArray jsonArray = new JSONArray(Collections.singletonList(imgUrl));
        System.out.println(jsonArray.toJSONString());
    }

    static String httpGetBody(String requestUrl) {
        // return HTTP_CLIENT.proxyGet(requestUrl, UTF8, heads());
        String htmlStr = DOWNLOAD.get(requestUrl);
        if (htmlStr.contains("<title>访问验证-安居客")) {
            return DOWNLOAD.get(requestUrl);
        }
        return htmlStr;
    }

    static Map<String, String> heads() {
        Map<String, String> head = new HashMap<>(16);
        head.put("Cookie", "aQQ_ajkguid=1C07AFCE-2D08-6FD9-B32E-19100FB88F00; id58=e87rkF+iQlyX3dROGukwAg==; 58tj_uuid=dd30cc7e-045c-4188-9929-3bb7c51148d5; _ga=GA1.2.303529338.1625127769; als=0; isp=true; wmda_uuid=b3ca69743f4ae3da2596b3a64590ebfc; wmda_new_uuid=1; wmda_visited_projects=%3B6289197098934; new_uv=8; ANJUKE_BUCKET=pc-home%3AErshou_Web_Home_Home-a; twe=2; sessid=228FC6B6-9C98-5674-B4E1-D1505333F3EA; ctid=11; obtain_by=1; xxzl_cid=0bdb92b075a049b3a3d6b926c719c0b1; xzuid=15b87591-d78b-4094-825c-6d7b09ecc5b5");
        return head;
    }
}
