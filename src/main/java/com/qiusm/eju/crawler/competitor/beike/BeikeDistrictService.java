package com.qiusm.eju.crawler.competitor.beike;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.base.CrawlerUrlUtils;
import com.qiusm.eju.crawler.base.dao.CrawlerUrlMapper;
import com.qiusm.eju.crawler.competitor.beike.dao.BkFenceMapper;
import com.qiusm.eju.crawler.competitor.beike.entity.BkFence;
import com.qiusm.eju.crawler.competitor.beike.entity.BkFenceExample;
import com.qiusm.eju.crawler.poi.gaode.GaodeService;
import com.qiusm.eju.crawler.poi.gaode.dao.GaodeCityFenceMapper;
import com.qiusm.eju.crawler.poi.gaode.dao.GaodeCityPoiInfoMapper;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFence;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFenceExample;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfoExample;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.SEMICOLON;
import static java.math.BigDecimal.ROUND_UP;

/**
 * 贝壳区域
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class BeikeDistrictService {

    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder().proxyUrl(PROXY_URL).connectTimeout(60000).readTimeout(60000).charset(GBK).builderHttp();

    protected static final List<String> ERROR_MSG = new ArrayList<>();

    private static final String BEIKE_FILE_ROOT = "source\\beike\\";
    private static final String BEIKE_JSON_ROOT = String.format("%s\\json\\", BEIKE_FILE_ROOT);

    @Resource
    private GaodeCityFenceMapper cityFenceMapper;

    @Resource
    private GaodeCityPoiInfoMapper cityPoiInfoMapper;

    @Resource
    private BkFenceMapper bkFenceMapper;

    @Resource
    private GaodeService gaodeService;

    static {
        ERROR_MSG.addAll(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)));
    }

    /**
     * 通过城市编码获取区域列表
     * https://map.ke.com/proxyApi/i.c-pc-webapi.ke.com/map/bubblelist?cityId=310000&dataSource=ESF&condition=&id=&groupType=district&maxLatitude=31.436675992838257&minLatitude=31.061270919403874&maxLongitude=121.92943412426429&minLongitude=121.04636387573557
     */
    static String bubble_url = "https://map.ke.com/proxyApi/i.c-pc-webapi.ke.com/map/bubblelist?";
    static String city_url = bubble_url + "cityId=%s&dataSource=ESF&condition=&id=&groupType=%s&maxLatitude=%s&minLatitude=%s&maxLongitude=%s&minLongitude=%s";

    public void city(String cityCode) {

        BkFenceExample bkFenceExample = new BkFenceExample();
        bkFenceExample.createCriteria().andCityCodeEqualTo(cityCode);
        if (bkFenceMapper.countByExample(bkFenceExample) > 0) {
            log.info("{}-已经抓取过了", cityCode);
            return;
        }

        GaodeCityPoiInfoExample poiInfoExample = new GaodeCityPoiInfoExample();
        poiInfoExample.createCriteria().andAdCodeEqualTo(cityCode);
        List<GaodeCityPoiInfo> infos = cityPoiInfoMapper.selectByExample(poiInfoExample);
        GaodeCityPoiInfo info = infos.get(0);

        if (info.getFenceId() == null) {
            gaodeService.cityFenceStart(info.getName());
            infos = cityPoiInfoMapper.selectByExample(poiInfoExample);
            info = infos.get(0);
        }

        GaodeCityFenceExample example = new GaodeCityFenceExample();
        example.createCriteria().andIdEqualTo(info.getFenceId());

        List<GaodeCityFence> cityFences = cityFenceMapper.selectByExampleWithBLOBs(example);
        GaodeCityFence cityFence = cityFences.get(0);

        // 根据围栏数据计算最大最小值
        TreeSet<String> lnSet = new TreeSet<>();
        TreeSet<String> latSet = new TreeSet<>();

        for (String singleBorder : cityFence.getFence().split(SEMICOLON)) {
            String[] var1 = singleBorder.split(COMMA);
            lnSet.add(var1[0]);
            latSet.add(var1[1]);
        }

        String minLng = lnSet.first();
        String minLat = latSet.first();

        String maxLng = lnSet.last();
        String maxLat = latSet.last();

        log.info("{}-{}-区域商圈围栏数据开始处理！", info.getName(), info.getAdCode());

        district(info, maxLat, minLat, maxLng, minLng);

        log.info("{}-{}-区域商圈围栏数据处理完毕！", info.getName(), info.getAdCode());
    }

    /**
     * 获取区域列表
     */
    public void district(GaodeCityPoiInfo cityPoiInfo, String... arg) {
        String cityCode = cityPoiInfo.getAdCode();
        String url = String.format(city_url, cityCode, "district", arg[0], arg[1], arg[2], arg[3]);
        String jsonStr = httpUtils.proxyGet(url, head(), (html) -> !checkBody(url, html, "district"));
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        JSONObject data = jsonObj.getJSONObject("data");
        JSONArray bubbleList = data.getJSONArray("bubbleList");

        if (bubbleList == null) {
            return;
        }

        String fileName = String.format("%s_district.json", cityCode);
        String filePath = String.format("%s\\%s\\", BEIKE_JSON_ROOT, cityCode);
        FileUtils.printFile(jsonObj.toJSONString(), filePath, fileName, false);
        bubbleList.forEach(o -> {
            JSONObject var = (JSONObject) o;
            String name = var.getString("name");
            String longitude = var.getString("longitude");
            String latitude = var.getString("latitude");
            String border = var.getString("border");
            BkFence fence = new BkFence();
            fence.setCityCode(cityPoiInfo.getAdCode());
            fence.setCityName(cityPoiInfo.getName());
            fence.setDistrict(name);
            fence.setLongitude(longitude);
            fence.setLatitude(latitude);
            fence.setFence(border);
            fence.setType("district");
            bkFenceMapper.insert(fence);
            bizcircle(fence, name, border, longitude, latitude);
        });
    }

    /**
     * 商圈获取
     *
     * @param disFence  区域围栏实体
     * @param name      区域名称
     * @param fenceStr  区域围栏数据
     * @param longitude 区域经度
     * @param latitude  区域纬度
     */
    public void bizcircle(BkFence disFence, String name, String fenceStr, String longitude, String latitude) {
        String cityCode = disFence.getCityCode();
        BigDecimal longitudeStep = new BigDecimal("0.096");
        BigDecimal latitudeStep = new BigDecimal("0.08");
        // 最多走几步
        BigDecimal maxStepNum = new BigDecimal("3");

        TreeSet<BigDecimal> lnSet = new TreeSet<>();
        TreeSet<BigDecimal> latSet = new TreeSet<>();

        for (String singleBorder : fenceStr.split(SEMICOLON)) {
            String[] var1 = singleBorder.split(COMMA);
            lnSet.add(new BigDecimal(var1[0]));
            latSet.add(new BigDecimal(var1[1]));
        }

        BigDecimal minLng = lnSet.first();
        BigDecimal minLat = latSet.first();

        BigDecimal maxLng = lnSet.last();
        BigDecimal maxLat = latSet.last();

        // 如果按照当前步长，走的步数超过3次，则步长进行调整。步长 = 长度 / 步数(3)
        if (maxLng.subtract(minLng).divide(longitudeStep, ROUND_UP).compareTo(maxStepNum) > 0) {
            longitudeStep = maxLng.subtract(minLng).divide(maxStepNum, ROUND_UP);
        }

        // 如果按照当前步长，走的步数超过3次，则步长进行调整。步长 = 长度 / 步数(3)
        if (maxLat.subtract(minLat).divide(latitudeStep, ROUND_UP).compareTo(maxStepNum) > 0) {
            latitudeStep = maxLat.subtract(minLat).divide(maxStepNum, ROUND_UP);
        }

        int i = 0;
        BigDecimal tempMaxLng = minLng.add(longitudeStep);
        StringBuilder latStr = new StringBuilder();
        StringBuilder lngStr = new StringBuilder();
        while (maxLng.compareTo(minLng) > 0) {
            BigDecimal tempMaxLat = minLat.add(latitudeStep);
            BigDecimal tempMinLat = new BigDecimal(minLat.toString());
            while (maxLat.compareTo(tempMinLat) > 0) {
                String fileName = String.format("%s_%s_%s_%s_biz.json", name, longitude, latitude, i++);
                String filePath = String.format("D:\\Temp\\beike\\json\\%s\\", cityCode);

                if (new File(filePath + fileName).exists()) {
                    continue;
                }

                latStr.append(tempMaxLat).append(",").append(tempMinLat).append(",");
                lngStr.append(tempMaxLng).append(",").append(minLng).append(",");
                log.info("{},{},{},{}", tempMaxLat, tempMinLat, tempMaxLng, minLng);

                // maxLatitude=%s&minLatitude=%s&maxLongitude=%s&minLongitude=%s
                String url = String.format(city_url, cityCode, "bizcircle", tempMaxLat, tempMinLat, tempMaxLng, minLng);
                String jsonStr = httpUtils.proxyGet(url, head(), (html) -> !checkBody(url, html, "bizcircle"));

                tempMinLat = tempMaxLat;
                tempMaxLat = tempMinLat.add(latitudeStep);

                JSONObject jsonObj = JSONObject.parseObject(jsonStr);

                JSONObject data = jsonObj.getJSONObject("data");
                JSONArray bubbleList = data.getJSONArray("bubbleList");
                if (bubbleList != null && !bubbleList.isEmpty()) {
                    FileUtils.printFile(jsonObj.toJSONString(), filePath, fileName, false);
                    bubbleList.forEach(o -> {
                        JSONObject var = (JSONObject) o;
                        BkFence fence = new BkFence();
                        fence.setCityCode(disFence.getCityCode());
                        fence.setCityName(disFence.getCityName());
                        fence.setDistrict(disFence.getDistrict());
                        fence.setBizcircle(var.getString("name"));
                        fence.setLongitude(var.getString("longitude"));
                        fence.setLatitude(var.getString("latitude"));
                        fence.setFence(var.getString("border"));
                        fence.setType("bizcircle");
                        bkFenceMapper.insert(fence);
                    });
                }

            }
            // 经度前移
            minLng = tempMaxLng;
            tempMaxLng = tempMaxLng.add(longitudeStep);
        }
        log.info("纬度列表：{}", latStr);
        log.info("经度列表：{}", lngStr);
    }

    public void jsonParser(String cityCode) {
        String filePath = String.format("%s\\%s\\", BEIKE_JSON_ROOT, cityCode);
        File file = new File(filePath);
        File[] jsonFile = file.listFiles();
        assert jsonFile != null;
        Set<String> name = new HashSet<>();
        Map<String, BigInteger> map = new HashMap<>(1);
        map.put("countInt", new BigInteger("0"));
        for (File file1 : jsonFile) {
            String jsonStr = FileUtils.readFile(file1);
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray bubbleList = data.getJSONArray("bubbleList");
            if (bubbleList == null) {
                continue;
            }
            bubbleList.forEach(o -> {
                JSONObject var = (JSONObject) o;
                String n = var.getString("name");

                if (!name.contains(n)) {
                    String countStr = var.getString("countStr");
                    countStr = countStr.replace("套", "");
                    if (countStr.endsWith("万")) {
                        countStr = countStr.replace("万", "");
                        countStr = String.valueOf(Double.parseDouble(countStr) * 10_000).split("\\.")[0];
                    }
                    map.put("countInt", map.get("countInt").add(new BigInteger(countStr)));
                    log.info("{},{}", n, countStr);
                    name.add(n);
                }
            });
        }
        log.info("共计商圈：{}; 共计套数：{}", name.size(), map.get("countInt").toString());
    }

    /**
     * 获取城市编码列表
     *
     * @return 城市编码列表
     */
    public List<String> cityList() {
        String filePath = "source\\beike\\bk_city_list.json";
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("城市列表json文件不存在！");
        }
        String jsonStr = FileUtils.readFile(file);
        List<String> cityCodes = new ArrayList<>();
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        JSONArray cityList = jsonObj.getJSONObject("data").getJSONArray("cityList");
        cityList.forEach(o -> {
            JSONObject var = (JSONObject) o;
            for (Object o1 : var.getJSONArray("list")) {
                JSONObject var1 = (JSONObject) o1;
                for (Object o2 : var1.getJSONArray("list")) {
                    JSONObject var2 = (JSONObject) o2;
                    cityCodes.add(var2.getString("id"));
                }
            }
        });
        return cityCodes;
    }

    Map<String, String> head() {
        Map<String, String> head = new HashMap<>(16);
        head.put("Accept", "application/json");
        head.put("Accept-Encoding", "utf-8");
        /*head.put("Accept-Language", "zh-CN,zh;q=0.9");
        head.put("Cache-Control", "no-cache");
        head.put("Connection", "keep-alive");*/
        head.put("Cookie", "lianjia_uuid=1ee42ee6-b2cc-4551-a06d-2b220f75c8af; lianjia_ssid=df414088-147a-47cd-804b-2ed3c83fede5; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2217a5706521830f-08bc507997ed05-3f356b-1327104-17a570652197d6%22%2C%22%24device_id%22%3A%2217a5706521830f-08bc507997ed05-3f356b-1327104-17a570652197d6%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; select_city=310000");
        head.put("Host", "map.ke.com");
        head.put("plat", "KE");
        head.put("Pragma", "no-cache");
        head.put("Referer", "https://map.ke.com/map/310000/ESF/");
        /*head.put("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        head.put("sec-ch-ua-mobile", "?0");
        head.put("Sec-Fetch-Dest", "empty");
        head.put("Sec-Fetch-Mode", "cors");
        head.put("Sec-Fetch-Site", "same-origin");
        head.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36");*/
        head.put("X-Requested-With", "XMLHttpRequest");
        return head;
    }

    private boolean checkBody(String requestUrl, String htmlStr, String type) {

        boolean success = true;

        for (String msg : ERROR_MSG) {
            if (StringUtils.startsWith(htmlStr, msg)) {
                success = false;
            }
        }

        if (!htmlStr.contains("reqId")) {
            success = false;
        }

        return success;
    }

}
