package com.qiusm.eju.crawler.poi.gaode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.base.CrawlerUrl;
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
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;
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

    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder().proxyUrl(PROXY_URL0).connectTimeout(60000).readTimeout(60000).charset(GBK).builderHttp();

    @Resource
    private GaodeDao dao;

    /**
     * 中国区所有城市的坐标 <br>
     * 结果参考：test/resources/poi/gaode/allCity.json <br>
     */
    public void allCityStart() {
        String body = httpGetBody(All_CITY_POI_URL, UTF8);
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
        ExecutorService fixedThreadPool = ThreadPoolUtils.newFixedThreadPool("fence", fixedNum, 10);
        try {
            for (GaodeCityFence cityFence : cityFences) {
                semaphore.acquire();
                fixedThreadPool.execute(() -> {
                    try {
                        String[] fenceArr = cityFence.getFence().split(PIPE);
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
     * @param tagMap   标签集合：key是标签编码，value是标签中文含义。参见{@link com.qiusm.eju.crawler.poi.gaode.constant.eumns.GaodePOIType}
     */
    public void callCityPoiInfo(String cityName, Map<String, String> tagMap) {
        // 根据城市查询城市的POI点
        GaodeCityPointExample example = new GaodeCityPointExample();
        example.createCriteria().andCityNameEqualTo(cityName);
        List<GaodeCityPoint> cityPoints = dao.selectCityPointByExample(example);
        callPoiInfo(cityPoints, tagMap);
    }

    public void callCityPoiInfoByLngAndLat(String longitude, String latitude, Map<String, String> tagMap) {
        GaodeCityPointExample example = new GaodeCityPointExample();
        example.createCriteria()
                .andLongitudeLike(longitude + "%")
                .andLatitudeLike(latitude + "%");
        List<GaodeCityPoint> cityPoints = dao.selectCityPointByExample(example);
        callPoiInfo(cityPoints, tagMap);
    }

    /**
     * 获取城市坐标点周边的相关信息
     *
     * @param cityPoints 城市坐标点
     * @param tagMap     标签集合
     */
    private void callPoiInfo(List<GaodeCityPoint> cityPoints, Map<String, String> tagMap) {
        final int fixedNum = 3;
        Semaphore semaphore = new Semaphore(fixedNum);
        ExecutorService fixedThreadPool = ThreadPoolUtils.newFixedThreadPool("poi_info", fixedNum, 10);

        try {
            for (GaodeCityPoint point : cityPoints) {
                semaphore.acquire();
                fixedThreadPool.execute(() -> {
                    try {
                        tagMap.forEach((tagCode, tagName) -> {
                            List<GaodePoi> pois = loadPoiInfo(point, tagCode, tagName);
                            if (pois.size() > 0) {
                                // 表示处理成功了
                                point.setStatus("ok");
                                dao.updateCityPointByKey(point);
                                dao.savePoi(pois);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                });
            }
            fixedThreadPool.shutdown();
            while (!fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * 基于 point & 标签编码 加载周边1.5km与当前标签相关的poi信息
     *
     * @param point   一个坐标点
     * @param tagCode 标签编码
     * @param tagName 标签名称
     * @return poi列表
     */
    private List<GaodePoi> loadPoiInfo(GaodeCityPoint point, String tagCode, String tagName) {
        int pageSize = 50;
        // 总页数，默认为1，当第1次请求的时候，尝试获取总记录数，从而换算出总的页数
        int totalPages = 1;
        String radius = "1500";
        List<GaodePoi> pois = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            int tryCount = 0;
            while (tryCount++ < 10) {
                //http://restapi.amap.com/v3/place/around?key=50fa7b34a8a07b5c46969979f260d109&location=116.473168,39.993015&keywords=&types=011100&radius=1000&offset=20&page=1&extensions=all
                String poiUrl = "http://restapi.amap.com/v3/place/around?location="
                        + point.getLocation()
                        + "&keywords=&types=" + tagCode
                        + "&radius=" + radius
                        + "&offset=" + pageSize
                        + "&extensions=all&page=" + i;
                String gdBody = httpGetBody(GaodeUtils.packageUrl(poiUrl));
                if (StringUtils.isNotBlank(gdBody)) {
                    if (gdBody.contains("strategy:priority_first use up limit")) {
                        try {
                            log.info("请求次数超限，等待5s，返回结果：" + gdBody);
                            Thread.sleep(5 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (gdBody.contains(RESULT_VO)) {
                        //解析结果
                        JSONObject mainJson = JSON.parseObject(gdBody);
                        mainJson = mainJson.getJSONObject(RESULT_VO);
                        if (null == mainJson) {
                            return pois;
                        }

                        String infoCode = mainJson.getString(INFO_CODE);
                        if (!(StringUtils.isNotBlank(infoCode)
                                && "10000".equals(infoCode))) {
                            continue;
                        }

                        // 翻页参数
                        if (totalPages == 1) {
                            // 总记录数
                            int totalNum = !mainJson.containsKey("count") ? 0 : mainJson.getInteger("count");
                            // 总页数
                            totalPages = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
                        }

                        if (mainJson.containsKey(POIS)) {
                            JSONArray poisJsonArray = JSON.parseArray(JSON.toJSONString(mainJson.get(POIS)));
                            poisJsonArray.forEach(var -> {
                                if (var instanceof JSONObject) {
                                    GaodePoi var1 = jsonObjToGaodePoiObj((JSONObject) var);
                                    //基础数据
                                    var1.setTagCode(tagCode);
                                    var1.setTagName(tagName);
                                    pois.add(var1);
                                } else {
                                    log.warn("对象不是[JSONObject]类型，{}", var);
                                }
                            });
                        } else {
                            log.info("标签: {}, 坐标: {}, 请求放回结果为空. url: {}", tagName, point, poiUrl);
                        }
                        break;
                    }
                }
            }
        }
        return pois;
    }

    /**
     * 通过 cityPoiInfo 获取当前城市的电子围栏信息
     *
     * @param cityPoiInfo 城市poi信息
     */
    private void loadCityFence(GaodeCityPoiInfo cityPoiInfo) {
        try {
            String poiUrl = String.format(CITY_POI_URL, cityPoiInfo.getName());
            String body = httpGetBody(poiUrl);
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

    /**
     * 对中国区所有城市的解析。将解析结果存储到 infos 集合中，后续存储到db中
     *
     * @param districts  当前城市
     * @param infos      城市信息集合
     * @param parentCode 上属城市的编码
     */
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

    /**
     * 将jsonObject转换为 GaodePoi 类
     *
     * @param var0 jsonObject
     * @return GaodePoi
     */
    private GaodePoi jsonObjToGaodePoiObj(JSONObject var0) {
        GaodePoi var1 = new GaodePoi();

        var1.setUid(var0.getString("id"));
        var1.setParent(var0.getString("parent"));
        var1.setChildType(var0.getString("childtype"));
        var1.setName(var0.getString("name"));
        var1.setTag(var0.getString("tag"));

        var1.setType(var0.getString("type"));
        var1.setTypeCode(var0.getString("typecode"));
        var1.setBizType(var0.getString("biz_type"));
        var1.setAddress(var0.getString("address"));
        var1.setLocation(var0.getString("location"));
        var1.setTel(var0.getString("tel"));

        var1.setPostCode(var0.getString("postcode"));
        var1.setWebsite(var0.getString("website"));
        var1.setEmail(var0.getString("email"));
        var1.setPname(var0.getString("pname"));
        var1.setPcode(var0.getString("pcode"));

        var1.setCityName(var0.getString("cityname"));
        var1.setCityCode(var0.getString("citycode"));
        var1.setAdname(var0.getString("adname"));
        var1.setAdcode(var0.getString("adcode"));
        var1.setImportance(var0.getString("importance"));

        var1.setShopId(var0.getString("shopid"));
        var1.setShopInfo(var0.getString("shopinfo"));
        var1.setPoiWeight(var0.getString("poiweight"));
        var1.setGridCode(var0.getString("gridcode"));
        var1.setDistance(var0.getString("distance"));

        var1.setBusinessArea(var0.getString("business_area"));
        var1.setNaviPoiid(var0.getString("navi_poiid"));
        var1.setEntrLocation(var0.getString("entr_location"));
        var1.setAlias(var0.getString("alias"));

        if (var0.containsKey("photos")) {
            StringBuilder photoSb = new StringBuilder();
            var0.getJSONArray("photos").forEach(photo -> {
                if (photo instanceof JSONObject) {
                    photoSb.append(((JSONObject) photo).getString("url")).append(",");
                }
            });

            if (photoSb.length() > 0) {
                var1.setPhotos(photoSb.toString());
            }
        }

        return var1;

    }

    private String httpGetBody(String requestUrl) {
        return httpGetBody(requestUrl, null);
    }

    private String httpGetBody(String requestUrl, String charset) {
        CrawlerUrl crawlerUrl = new CrawlerUrl(requestUrl, "gaode_poi", "3");
        crawlerUrl.insert();
        return httpUtils.get(requestUrl, charset, 100_000, 50_000, null);
    }

}
