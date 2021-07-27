package com.qiusm.eju.crawler.constant.poi;

/**
 * 高德地图相关的URL模板
 *
 * @author qiushengming
 */
public class GaodeUrl {
    /**
     * 中国区所有城市坐标的请求URL
     */
    public static final String All_CITY_POI_URL = "https://restapi.amap.com/v3/config/district?keywords=中华人民共和国&subdistrict=3&extensions=all&key=81ed3fefe0a460612cbb8d594f35f337";

    /**
     * 获取单个城市的URL
     */
    public static final String CITY_POI_URL = "https://restapi.amap.com/v3/config/district?keywords=%s&subdistrict=0&extensions=all&key=81ed3fefe0a460612cbb8d594f35f337";
}
