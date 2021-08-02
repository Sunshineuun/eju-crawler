package com.qiusm.eju.crawler.utils.poi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.service.poi.ILocationKeyService;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.qiusm.eju.crawler.utils.poi.GaodeUtils.packageUrl;

/**
 * 坐标系转换
 *
 * @author qiushengming
 */
@Component
public class LocationConvertUtils {

    private static ILocationKeyService locationKeyService;

    public static Logger LOGGER = LoggerFactory.getLogger(LocationConvertUtils.class);

    /**
     * 内部路径
     */
    public final static String preFix = "http://mapdata-api.ejudata.com/inner/map/get?url=";

    @Autowired
    public void setLocationKeyService(ILocationKeyService locationKeyService) {
        LocationConvertUtils.locationKeyService = locationKeyService;
    }

    /**
     * 将输入坐标转换为高德坐标点
     *
     * @param sourceLocation（源坐标点，多个以分号;分割）
     * @param coordsys（源坐标点类型：gps、mapbar、baidu、autonavi(不进行转换)）
     * @param resultLength（放回坐标点长度）
     * @param okHttpUtils                                       httpUtils
     * @return 高德坐标点
     */
    public static String gdInternalConvertOfLength(String sourceLocation, String coordsys, Integer resultLength, OkHttpUtils okHttpUtils) {
        StringBuilder resultLocation = new StringBuilder();
        if (StringUtils.isNotBlank(sourceLocation)) {
            String[] locationArr = sourceLocation.split(";");
            if (locationArr.length <= 40) {
                resultLocation = new StringBuilder(gdInternalConvert(sourceLocation, coordsys, resultLength, okHttpUtils));
                return resultLocation.toString();
            } else {
                StringBuilder splitLocation = new StringBuilder();
                for (int i = 0; i < locationArr.length; i++) {
                    if ((i + 1) % 40 == 0) {
                        splitLocation.append(locationArr[i]);
                        resultLocation.append(gdInternalConvert(splitLocation.toString(), coordsys, resultLength, okHttpUtils)).append(";");
                        splitLocation = new StringBuilder();
                    } else {
                        splitLocation.append(locationArr[i]).append(";");
                    }
                }

                if (StringUtils.isNotBlank(splitLocation.toString()) && splitLocation.length() > 0) {
                    splitLocation = new StringBuilder(splitLocation.substring(0, splitLocation.length() - 1));
                    resultLocation.append(gdInternalConvert(splitLocation.toString(), coordsys, resultLength, okHttpUtils)).append(";");
                }
            }
        }
        if (StringUtils.isNotBlank(resultLocation.toString()) && resultLocation.length() > 0) {
            resultLocation = new StringBuilder(resultLocation.substring(0, resultLocation.length() - 1));
        }
        return resultLocation.toString();
    }

    /**
     * 百度坐标点转换
     *
     * @param from（源坐标类型：<br>                                1：GPS设备获取的角度坐标，WGS84坐标;<br>
     *                                                       2：GPS获取的米制坐标、sogou地图所用坐标;<br>
     *                                                       3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局（GCJ02）坐标;<br>
     *                                                       4：3中列表地图坐标对应的米制坐标;<br>
     *                                                       5：百度地图采用的经纬度坐标; <br>
     *                                                       6：百度地图采用的米制坐标;<br>
     *                                                       7：mapbar地图坐标;<br>
     *                                                       8：51地图坐标）<br>
     * @param to（目标坐标类型：5：bd09ll(百度经纬度坐标);bd09mc(百度米制经纬度坐标)）
     * @param sourceLocation（源坐标点，多个以分号;分割）
     * @param okHttpUtils                                    okHttpUtils
     * @return
     */
    public static String bdInternalConvertOfLength(String from, String to, String sourceLocation, Integer resultLength, OkHttpUtils okHttpUtils) {
        StringBuilder resultLocation = new StringBuilder();
        if (StringUtils.isNotBlank(sourceLocation)) {
            String[] locationArr = sourceLocation.split(";");
            if (locationArr.length <= 40) {
                resultLocation = new StringBuilder(bdInternalConvert(from, to, sourceLocation, resultLength, okHttpUtils));
                return resultLocation.toString();
            } else {
                StringBuilder splitLocation = new StringBuilder();
                for (int i = 0; i < locationArr.length; i++) {
                    if ((i + 1) % 40 == 0) {
                        splitLocation.append(locationArr[i]);
                        resultLocation.append(bdInternalConvert(from, to, splitLocation.toString(), resultLength, okHttpUtils)).append(";");
                        splitLocation = new StringBuilder();
                    } else {
                        splitLocation.append(locationArr[i]).append(";");
                    }
                }

                if (StringUtils.isNotBlank(splitLocation.toString()) && splitLocation.length() > 0) {
                    splitLocation = new StringBuilder(splitLocation.substring(0, splitLocation.length() - 1));
                    resultLocation.append(bdInternalConvert(from, to, splitLocation.toString(), resultLength, okHttpUtils)).append(";");
                }
            }
        }
        if (StringUtils.isNotBlank(resultLocation.toString()) && resultLocation.length() > 0) {
            resultLocation = new StringBuilder(resultLocation.substring(0, resultLocation.length() - 1));
        }
        return resultLocation.toString();
    }

    /**
     * 高德坐标点转换
     *
     * @param sourceLocaiton（源坐标点，多个以分号;分割，长度不超过40个）
     * @param coordsys（源坐标点类型：gps、                   mapbar、baidu、autonavi(不进行转换)）
     * @param resultLength（放回坐标点长度）
     * @param okHttpUtils                            okHttpUtils
     * @return 高德坐标点
     */
    private static String gdInternalConvert(String sourceLocaiton, String coordsys, Integer resultLength, OkHttpUtils okHttpUtils) {
        if (StringUtils.isNotBlank(sourceLocaiton) && sourceLocaiton.length() > 0) {
            if ((sourceLocaiton.lastIndexOf(";") + 1) == sourceLocaiton.length()) {
                // sourceLocaiton.substring(0, sourceLocaiton.length() - 1);
            }
        } else {
            return "";
        }
        //返回结果
        StringBuilder resultLocation = new StringBuilder();
        //请求转换接口
        String requestUrl = "https://restapi.amap.com/v3/assistant/coordinate/convert?locations=" + sourceLocaiton + "&coordsys=" + coordsys + "&output=json";
        int tryCount = 0;
        while (tryCount < 10) {
            String body = okHttpUtils.get(packageUrl(requestUrl));
            tryCount++;
            //解析放回结果
            if (StringUtils.isNotBlank(body) && !body.contains("ejuResponseCode")) {
                JSONObject object = JSON.parseObject(body);
                object = object.getJSONObject("resultVo");
                String code = object.get("infocode").toString();
                String status = object.get("status").toString();
                String info = object.get("info").toString();
                if (!"10000".equals(code) && status.equals("1")) {
                    LOGGER.info("=============请求出错：" + info);
                } else {
                    String locations = String.valueOf(object.get("locations"));
                    if (StringUtils.isNotBlank(locations) && locations.length() > 10) {
                        String[] fenceArr = locations.split(";");
                        for (String location : fenceArr) {
                            String[] locationArr = location.split(",");
                            String lng = locationArr[0];
                            String lat = locationArr[1];

                            //保留小数位
                            if (null != resultLength) {
                                int spiltLength = resultLength + 1;
                                if (StringUtils.isNotBlank(lng) && lng.contains(".")) {
                                    int fractional = lng.length() - lng.indexOf(".");
                                    if (fractional > spiltLength) {
                                        lng = lng.substring(0, lng.indexOf(".") + spiltLength);
                                    }
                                }
                                if (StringUtils.isNotBlank(lat) && lat.contains(".")) {
                                    int fractional = lat.length() - lat.indexOf(".");
                                    if (fractional > spiltLength) {
                                        lat = lat.substring(0, lat.indexOf(".") + spiltLength);
                                    }
                                }
                            }
                            resultLocation.append(lng).append(",").append(lat).append(";");
                        }
                    }
                    break;
                }
            }
        }
        if (StringUtils.isNotBlank(resultLocation.toString()) && resultLocation.length() > 0) {
            resultLocation = new StringBuilder(resultLocation.substring(0, resultLocation.length() - 1));
        }
        return resultLocation.toString();
    }

    /**
     * 百度坐标点转换
     *
     * @param from（源坐标类型：1：GPS设备获取的角度坐标，WGS84坐标; 2：GPS获取的米制坐标、sogou地图所用坐标;3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局（GCJ02）坐标;
     *                                           4：3中列表地图坐标对应的米制坐标;5：百度地图采用的经纬度坐标; 6：百度地图采用的米制坐标;7：mapbar地图坐标;8：51地图坐标）
     * @param to（目标坐标类型：                         5：bd09ll(百度经纬度坐标);bd09mc(百度米制经纬度坐标)）
     * @param sourceLocation                     sourceLocation
     * @param okHttpUtils                        okHttpUtils
     * @return 百度坐标点
     */
    private static String bdInternalConvert(String from, String to, String sourceLocation, Integer resultLength, OkHttpUtils okHttpUtils) {
        StringBuilder resultLocation = new StringBuilder();
        try {
            //百度key
            String baiduAk = "";
            List<String> baiduList = locationKeyService.getBaiduList();
            if (null != baiduList && baiduList.size() > 0) {
                baiduAk = baiduList.get(0);
            }
            int tryCount = 0;
            while (tryCount < 10) {
                String url = "http://api.map.baidu.com/geoconv/v1/?coords=" + sourceLocation + "&from=" + from + "&to=" + to + "&ak=" + baiduAk;
                String body = okHttpUtils.proxyGet(url);
                tryCount++;
                if (StringUtils.isNotBlank(body) && !body.contains("ejuResponseCode")) {
                    JSONObject object = JSON.parseObject(body);
                    JSONArray jsonArray = object.getJSONArray("result");
                    String status = object.getString("status");
                    if (!"0".equals(status)) {
                        LOGGER.info("===========百度转换失败：" + baiduAk + "===============");
                        assert baiduList != null;
                        baiduList.remove(baiduAk);
                        baiduAk = baiduList.get(0);
                    } else {
                        object = JSON.parseObject(body);
                        jsonArray = object.getJSONArray("result");
                        if (null != jsonArray && jsonArray.size() > 0) {
                            for (int j = 0; j < jsonArray.size(); j++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(j);
                                String lng = jsonObject.getString("x");
                                String lat = jsonObject.getString("y");
                                //保留小数位
                                if (null != resultLength) {
                                    int spiltLength = resultLength + 1;
                                    if (StringUtils.isNotBlank(lng) && lng.contains(".")) {
                                        int fractional = lng.length() - lng.indexOf(".");
                                        if (fractional > spiltLength) {
                                            lng = lng.substring(0, lng.indexOf(".") + spiltLength);
                                        }
                                    }
                                    if (StringUtils.isNotBlank(lat) && lat.contains(".")) {
                                        int fractional = lat.length() - lat.indexOf(".");
                                        if (fractional > spiltLength) {
                                            lat = lat.substring(0, lat.indexOf(".") + spiltLength);
                                        }
                                    }
                                }
                                resultLocation.append(lng).append(",").append(lat).append(";");
                            }
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(resultLocation.toString()) && resultLocation.length() > 0) {
            resultLocation = new StringBuilder(resultLocation.substring(0, resultLocation.length() - 1));
        }
        return resultLocation.toString();
    }

    /**
     * 获取高德坐标点
     *
     * @param address
     * @param city
     * @return
     */
    public static String getGdLocation(String address, String city, OkHttpUtils okHttpUtils) {
        String resultLocation = null;

        String gaodeUrl = "https://restapi.amap.com/v3/geocode/geo?&address=" + address + "&city=" + city + "packageUrl";
        int tryCount = 0;
        while (tryCount++ < 10) {
            String gaodeBody = okHttpUtils.get(packageUrl(gaodeUrl));
            if (StringUtils.isNotBlank(gaodeBody) && gaodeBody.contains("geocodes")) {
                JSONObject gaodeObj = JSONObject.parseObject(gaodeBody);
                gaodeObj = gaodeObj.getJSONObject("resultVo");
                JSONArray geocodesArr = gaodeObj.getJSONArray("geocodes");
                if (null != geocodesArr && geocodesArr.size() > 0) {
                    JSONObject geocodeObj = geocodesArr.getJSONObject(0);
                    resultLocation = geocodeObj.getString("location");
                }
                break;
            }
        }

        return resultLocation;
    }

    /**
     * 保留坐标点位数  121.123123123  ——> 121.123123
     *
     * @param lng    lng
     * @param length length
     * @return
     */
    public static String strSubString(String lng, int length) {
        try {
            int corNum = length + 1;
            if (StringUtils.isNotBlank(lng) && (lng.length() - lng.indexOf(".")) > corNum) {
                lng = lng.substring(0, lng.indexOf(".") + corNum);
            }
        } catch (Exception e) {
            LOGGER.error("LocationConvertUtils strSubString() 坐标点长度截取异常：" + e.getMessage());
        }
        return lng;
    }
}
