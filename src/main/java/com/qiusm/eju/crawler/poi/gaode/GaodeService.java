package com.qiusm.eju.crawler.poi.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.poi.gaode.dao.GaodeDao;
import com.qiusm.eju.crawler.poi.gaode.entity.*;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.*;
import static com.qiusm.eju.crawler.poi.gaode.constant.GaodeField.*;
import static com.qiusm.eju.crawler.poi.gaode.constant.GaodeUrl.All_CITY_POI_URL;
import static com.qiusm.eju.crawler.poi.gaode.constant.GaodeUrl.CITY_POI_URL;
import static java.math.BigDecimal.ROUND_UP;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class GaodeService {

    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder().proxyUrl(PROXY_URL).connectTimeout(60000).readTimeout(60000).charset(GBK).builderHttp();

    @Resource
    private GaodeDao dao;

    /**
     * 中国区所有城市的坐标 <br>
     * 结果参考：test/resources/poi/gaode/allCity.json <br>
     */
    public void allCityStart() {
        String body = httpUtils.proxyGet(All_CITY_POI_URL, UTF8, 10000, 10000, 5, null);
        if (StringUtils.isNotBlank(body)) {
            JSONObject bodyObj = JSONObject.parseObject(body);
            JSONArray districts = bodyObj.getJSONArray("districts");
            List<GaodeCityPoiInfo> infos = new ArrayList<>();
            parserDistrict(districts, infos, "0");
            // 将结果存储到数据库中 TODO
            dao.saveCityInfo(infos);
        }
    }

    /**
     * 遍历所有的城市集合,获取城市围栏数据.
     */
    public void allCityFenceStart() {
        List<GaodeCityPoiInfo> var1List = dao.selectAllCityInfo();
        var1List.forEach(this::loadCityFence);
    }

    /**
     * 获取单个城市的围栏数据
     *
     * @param cityName 城市标准名称
     */
    public void cityFenceStart(String cityName) {
        GaodeCityPoiInfoExample example = new GaodeCityPoiInfoExample();
        example.createCriteria().andNameEqualTo(cityName);
        List<GaodeCityPoiInfo> var3 = dao.selectCityInfoExample(example);
        var3.forEach(this::loadCityFence);
    }

    /**
     * 对电子围栏进行切割
     */
    public void cuttingAllCityElectronicFence(String cityName) {
        GaodeCityFenceExample example = new GaodeCityFenceExample();
        example.createCriteria().andNameEqualTo(cityName);
        List<GaodeCityFence> cityFences = dao.selectCityFenceByExample(example);
        final int fixedNum = 8;
        Semaphore semaphore = new Semaphore(fixedNum);
        ExecutorService fixedThreadPool = ThreadPoolUtils.newFixedThreadPool("fence", fixedNum, 1000);
        try {
            for (GaodeCityFence cityFence : cityFences) {
                semaphore.acquire();
                fixedThreadPool.execute(() -> {
                    try {
                        String[] fenceArr = cityFence.getFence().split(PIPE);
                        Date nowDate = new Date();

                        for (String fenceStr : fenceArr) {
                            // 经度(longitude)：每0.012=1000m，纬度(latitude)：每0.01=1000m
                            BigDecimal longitude = new BigDecimal("0.012");
                            BigDecimal latitude = new BigDecimal("0.01");
                            // 切割
                            List<String> points = cuttingPoints(fenceStr, longitude, latitude);

                            List<String> inPoints = isInPolygon(points, fenceStr);
                            // 存储有效的点
                            List<GaodeCityPoint> cityPoints = new ArrayList<>();
                            inPoints.forEach(var -> {
                                String[] var1 = var.split(COMMA);
                                GaodeCityPoint point = new GaodeCityPoint();
                                point.setLongitude(var1[0]);
                                point.setLatitude(var1[1]);
                                point.setCityName(cityFence.getName());
                                point.setCreateTime(nowDate);
                                cityPoints.add(point);
                            });
                            dao.saveCityPoint(cityPoints);
                        }
                        log.info("{} 切割围栏完毕.", cityFence.getName());
                    } catch (Exception e) {
                        log.error("城市围栏数据切割异常.{}", e.getMessage());
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                });
            }
            fixedThreadPool.shutdown();

            while (!fixedThreadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * 根据切割后的点，获取城市的POI信息
     * TODO
     * 具体实现待补充 <br>
     *
     * @param cityName 城市名称
     */
    public void callCityPoiInfo(String cityName) {
        final int fixedNum = 3;
        Semaphore semaphore = new Semaphore(fixedNum);
        ExecutorService fixedThreadPool = ThreadPoolUtils.newFixedThreadPool("poi_info", fixedNum, 1000);
        // 请求类型 获取哪些类型的POI需要在这里设定
        Map<String, String> gdMap = new HashMap<>(1);
        // 根据城市查询城市的POI点 TODO

        try {
            /*for (PoiMapLocationGaode poiMapLocation : poiMapLocations) {
                semaphore.acquire();
                fixedThreadPool.execute(() -> {
                    try {
                        String location_gaode = poiMapLocation.getGaodeLocation();
                        if (StringUtils.isBlank(location_gaode)) {
                            return;
                        }

                        for (Map.Entry<String, String> entry : gdMap.entrySet()) {
                            String sourceType = entry.getKey();
                            String sourceDesc = gdMap.get(sourceType);
                            //高德poi
                            boolean result = getGdPoi(location_gaode, sourceType, sourceDesc, httpClient);

                            if (result) {
                                poiMapLocation.setStatus("1");
                                poiMapLocationGaodeMapper.updateByPrimaryKeySelective(poiMapLocation);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                });
            }*/


            fixedThreadPool.shutdown();
            while (!fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {

        }

    }

    /**
     * 从切割出来的点中选择在围栏范围内的点 <br>
     *
     * @param points   切割出来的点
     * @param fenceStr 电子围栏
     * @return 返回在电子围栏中的点
     */
    private List<String> isInPolygon(List<String> points, String fenceStr) {
        List<Point2D.Double> point2Ds = new ArrayList<>();
        for (String s : fenceStr.split(SEMICOLON)) {
            String[] var1 = s.split(COMMA);
            Point2D.Double var = new Point2D.Double(Double.parseDouble(var1[0]), Double.parseDouble(var1[1]));
            point2Ds.add(var);
        }

        List<String> var5 = new ArrayList<>();
        points.forEach(point -> {
            String[] var1 = point.split(COMMA);
            Point2D.Double var = new Point2D.Double(Double.parseDouble(var1[0]), Double.parseDouble(var1[1]));
            if (GaodeUtils.isInPolygon(var, point2Ds)) {
                var5.add(point);
            }
        });

        return var5;
    }

    private void loadCityFence(GaodeCityPoiInfo cityPoiInfo) {
        try {
            String poiUrl = String.format(CITY_POI_URL, cityPoiInfo.getName());
            String body = httpUtils.proxyGet(poiUrl, "UTF-8", 10000, 10000, 5, null);
            if (StringUtils.isNotBlank(body)) {
                JSONObject bodyObj = JSONObject.parseObject(body);
                JSONArray districts = bodyObj.getJSONArray("districts");
                JSONObject districtObj = (JSONObject) districts.get(0);
                String fence = districtObj.getString("polyline");
                dao.saveCityFence(cityPoiInfo, fence);
                log.info("{}-电子围栏数据加载成功.", cityPoiInfo.getName());
            }
        } catch (Exception e) {
            log.error("获取：{}电子围栏数据失败。错误简要信息：{}", cityPoiInfo.getName(), e);
            e.printStackTrace();
        }
    }

    private void parserDistrict(JSONArray districts, List<GaodeCityPoiInfo> infos, String parentCode) {
        districts.forEach(o -> {
            if (!(o instanceof JSONObject)) {
                return;
            }
            GaodeCityPoiInfo cityPoiInfo = new GaodeCityPoiInfo();
            JSONObject var1 = (JSONObject) o;
            Object cityCode = var1.get(CITY_CODE);
            if (cityCode instanceof String) {
                cityPoiInfo.setCityCode((String) cityCode);
            }
            cityPoiInfo.setAdCode(var1.getString(CITY_AD_CODE));
            cityPoiInfo.setName(var1.getString(CITY_NAME));
            cityPoiInfo.setCenter(var1.getString(CENTER));
            cityPoiInfo.setLevel(var1.getString(LEVEL));
            cityPoiInfo.setParentCode(parentCode);
            infos.add(cityPoiInfo);

            String fence = var1.getString(POLYLINE);
            if (StringUtils.isNotEmpty(fence)) {
                dao.saveCityFence(cityPoiInfo, fence);
            }

            JSONArray var2 = var1.getJSONArray(DISTRICTS);
            if (!var2.isEmpty()) {
                parserDistrict(var2, infos, var1.getString(CITY_AD_CODE));
            }
        });
    }

    /**
     * 1. 经度最大值-经度最小值 / 经度步长； <br>
     * 2. 纬度最大值-纬度最小值 / 纬度步长；<br>
     * 一个矩形：<br>
     * 对长进行分段，长 = (经度最大值-经度最小值) <br>
     * 对宽进行分段，宽 = (纬度最大值-纬度最小值) <br>
     * 这样操作最后得到的点，可能会包含非当前围栏中的值 <br>
     * 经过验证，计算出来的点，不是特别的准确 <br>
     *
     * @param fenceStr      电子围栏数据
     * @param longitudeStep 经度步长
     * @param latitudeStep  维度步长
     * @return 切割后的坐标点列表
     */
    private List<String> cuttingPoints(String fenceStr, BigDecimal longitudeStep, BigDecimal latitudeStep) {

        TreeSet<BigDecimal> lnSet = new TreeSet<>();
        TreeSet<BigDecimal> latSet = new TreeSet<>();
        List<String> locations = new ArrayList<>();

        for (String singleBorder : fenceStr.split(SEMICOLON)) {
            String[] var1 = singleBorder.split(COMMA);
            lnSet.add(new BigDecimal(var1[0]));
            latSet.add(new BigDecimal(var1[1]));
        }

        BigDecimal maxLng = lnSet.last();
        BigDecimal minLng = lnSet.first();
        BigDecimal maxLat = latSet.last();
        BigDecimal minLat = latSet.first();

        /*
         * 注意舍入模式
         * (max - min) / step 取整
         * 向上取整
         */
        int subLong = maxLng.subtract(minLng).divide(longitudeStep, ROUND_UP).intValue();
        int subLat = maxLat.subtract(minLat).divide(latitudeStep, ROUND_UP).intValue();

        for (int i = 0; i <= subLong; i++) {
            BigDecimal lngMultiply = longitudeStep.multiply(new BigDecimal(Integer.toString(i)));
            BigDecimal lng = minLng.add(lngMultiply);

            for (int j = 0; j <= subLat; j++) {
                //精度问题
                BigDecimal multiply = latitudeStep.multiply(new BigDecimal(Integer.toString(j)));
                BigDecimal lat = minLat.add(multiply);
                locations.add(lng + "," + lat);
            }
        }
        return locations;
    }
}
