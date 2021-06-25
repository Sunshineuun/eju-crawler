package com.qiusm.eju.crawler.poi.gaode.constant;

/**
 * 高德解析定义的字段名称
 *
 * @author qiushengming
 */
public class GaodeField {

    public static final String CITY_CODE = "citycode";
    public static final String CITY_AD_CODE = "adcode";
    public static final String CITY_NAME = "name";
    public static final String POLYLINE = "polyline";
    public static final String CENTER = "center";
    public static final String LEVEL = "level";
    public static final String DISTRICTS = "districts";

    /**
     * 获取POI信息时的JSON字段名，用于取到响应结果
     */
    public static final String RESULT_VO = "resultVo";
    /**
     * 获取POI信息时的JSON字段名，用于取到状态码，判断是否请求成功
     */
    public static final String INFO_CODE = "infocode";
    /**
     * 获取POI信息时的JSON字段名，用于取到poi info array
     */
    public static final String POIS = "pois";

}
