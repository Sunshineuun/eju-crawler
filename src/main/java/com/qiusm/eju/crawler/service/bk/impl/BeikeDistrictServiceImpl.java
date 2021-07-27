package com.qiusm.eju.crawler.service.bk.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.bk.BkFence;
import com.qiusm.eju.crawler.poi.gaode.GaodeService;
import com.qiusm.eju.crawler.poi.gaode.dao.GaodeCityFenceMapper;
import com.qiusm.eju.crawler.poi.gaode.dao.GaodeCityPoiInfoMapper;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFence;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFenceExample;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfoExample;
import com.qiusm.eju.crawler.service.bk.BeikeBaseService;
import com.qiusm.eju.crawler.service.bk.IBeikeDistrictService;
import com.qiusm.eju.crawler.service.bk.IBkFenceService;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.SEMICOLON;
import static java.math.BigDecimal.ROUND_UP;

/**
 * 贝壳区域 围栏信息
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class BeikeDistrictServiceImpl
        extends BeikeBaseService
        implements IBeikeDistrictService {

    private static final String BEIKE_JSON_ROOT = String.format("%s\\json\\district", BEIKE_FILE_ROOT);

    @Resource
    private GaodeCityFenceMapper cityFenceMapper;

    @Resource
    private GaodeCityPoiInfoMapper cityPoiInfoMapper;

    @Resource
    private IBkFenceService bkFenceService;

    @Resource
    private GaodeService gaodeService;


    /**
     * 通过城市编码获取区域列表
     * https://map.ke.com/proxyApi/i.c-pc-webapi.ke.com/map/bubblelist?cityId=310000&dataSource=ESF&condition=&id=&groupType=district&maxLatitude=31.436675992838257&minLatitude=31.061270919403874&maxLongitude=121.92943412426429&minLongitude=121.04636387573557
     */
    static String bubble_url = "https://map.ke.com/proxyApi/i.c-pc-webapi.ke.com/map/bubblelist?";
    static String city_url = bubble_url + "cityId=%s&dataSource=ESF&condition=&id=&groupType=%s&maxLatitude=%s&minLatitude=%s&maxLongitude=%s&minLongitude=%s";

    /**
     * 根据城市ID，获取城市区域板块围栏信息；最大最小经纬度根据高德地图围栏信息计算得出。 <br>
     *
     * @param cityCode 城市编码
     */
    @Override
    public void city(String cityCode) {

        if (isLoadFence(cityCode)) {
            return;
        }

        GaodeCityPoiInfo info = loadCityPoiInfo(cityCode);
        if (info == null) {
            return;
        }

        // 根据围栏数据计算最大最小值
        // 计算最大最小
        BigDecimal[][] mm = calculateMaxMin(loadFence(info).getFence());

        BigDecimal minLng = mm[0][1];
        BigDecimal minLat = mm[1][1];

        BigDecimal maxLng = mm[0][0];
        BigDecimal maxLat = mm[1][0];

        log.info("{}-{}-区域商圈围栏数据开始处理！", info.getName(), info.getAdCode());

        districtPrivate(info, maxLat.toString(), minLat.toString(), maxLng.toString(), minLng.toString());

        log.info("{}-{}-区域商圈围栏数据处理完毕！", info.getName(), info.getAdCode());
    }

    /**
     * 根据城市ID和给定最大最小经纬度，获取城市区域板块围栏信息 <br>
     * maxLat, minLat, maxLng, minLng 经纬度顺序 <br>
     *
     * @param cityCode 城市编码
     * @param arg      最大最小经纬度数据
     */
    @Override
    public void district(String cityCode, String... arg) {
        GaodeCityPoiInfo info = loadCityPoiInfo(cityCode);
        if (info == null) {
            return;
        }
        districtPrivate(info, arg);
    }

    /**
     * 获取区域列表
     */
    @Override
    public void districtPrivate(GaodeCityPoiInfo cityPoiInfo, String... arg) {
        String cityCode = cityPoiInfo.getAdCode();
        String url = String.format(city_url, cityCode, "district", arg[0], arg[1], arg[2], arg[3]);
        String jsonStr = httpUtils.proxyGet(url, head(), (html) -> check(url, html, "district"));
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
            fence.insert();
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
        BigDecimal maxStepNum = new BigDecimal("2");

        // 计算最大最小
        BigDecimal[][] mm = calculateMaxMin(fenceStr);

        BigDecimal minLng = mm[0][1];
        BigDecimal minLat = mm[1][1];

        BigDecimal maxLng = mm[0][0];
        BigDecimal maxLat = mm[1][0];

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
                String filePath = String.format("%s\\%s\\", BEIKE_JSON_ROOT, cityCode);

                if (new File(filePath + fileName).exists()) {
                    continue;
                }

                latStr.append(tempMaxLat).append(",").append(tempMinLat).append(",");
                lngStr.append(tempMaxLng).append(",").append(minLng).append(",");
                log.debug("{},{},{},{}", tempMaxLat, tempMinLat, tempMaxLng, minLng);

                // maxLatitude=%s&minLatitude=%s&maxLongitude=%s&minLongitude=%s
                String url = String.format(city_url, cityCode, "bizcircle", tempMaxLat, tempMinLat, tempMaxLng, minLng);
                String jsonStr = httpUtils.proxyGet(url, head(), (html) -> check(url, html, "bizcircle"));

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
                        fence.insert();
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

    private BigDecimal[][] calculateMaxMin(String fenceStr) {
        TreeSet<BigDecimal> lnSet = new TreeSet<>();
        TreeSet<BigDecimal> latSet = new TreeSet<>();

        for (String singleBorder : fenceStr.split(SEMICOLON)) {
            String[] var1 = singleBorder.split(COMMA);
            lnSet.add(new BigDecimal(var1[0]));
            latSet.add(new BigDecimal(var1[1]));
        }

        return new BigDecimal[][]{
                {lnSet.first(), lnSet.last()}, {latSet.first(), latSet.last()}
        };
    }

    @Override
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
    @Override
    public List<String> loadCityList() {
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

    private GaodeCityPoiInfo loadCityPoiInfo(String cityCode) {
        GaodeCityPoiInfoExample poiInfoExample = new GaodeCityPoiInfoExample();
        poiInfoExample.createCriteria().andAdCodeEqualTo(cityCode);
        List<GaodeCityPoiInfo> infos = cityPoiInfoMapper.selectByExample(poiInfoExample);

        if (infos.size() <= 0) {
            log.error("没有该编码的城市信息：{}", cityCode);
            return null;
        }

        GaodeCityPoiInfo info = infos.get(0);

        if (info.getFenceId() == null) {
            gaodeService.cityFenceStart(info.getName());
            return loadCityPoiInfo(cityCode);
        }

        return info;
    }

    private GaodeCityFence loadFence(GaodeCityPoiInfo info) {
        GaodeCityFenceExample example = new GaodeCityFenceExample();
        example.createCriteria().andIdEqualTo(info.getFenceId());

        List<GaodeCityFence> cityFences = cityFenceMapper.selectByExampleWithBLOBs(example);
        return cityFences.get(0);
    }

    private Map<String, String> head() {
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

    private boolean check(String requestUrl, String htmlStr, String type) {

        boolean success = true;

        for (String msg : ERROR_MSG) {
            if (StringUtils.startsWith(htmlStr, msg)) {
                success = false;
            }
        }

        if (!htmlStr.contains("reqId")) {
            success = false;
        }

        return !success;
    }

    /**
     * 是否已经加载了区域板块的围栏数据
     *
     * @return 已加载：true;未加载：false
     */
    private boolean isLoadFence(String cityCode) {
        EntityWrapper<BkFence> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("city_code", cityCode);
        if (bkFenceService.selectCount(entityWrapper) > 0) {
            log.info("{}-已经抓取过了", cityCode);
            return true;
        }
        return false;
    }

}
