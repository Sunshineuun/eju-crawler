package com.qiusm.eju.crawler.constant.government;

import java.time.format.DateTimeFormatter;

/**
 * @author qiushengming
 */
public final class NanJinConstant {
    /**
     * 城市名称
     */
    public static final String CITY_NAME = "南京";

    /**
     * 重试次数定义
     */
    public static final int TRY_NUM = 5;

    public static final String NAN_JIN_ROOT_PATH = "D:\\Temp\\CRAWLER\\NAN_JIN\\";
    /**
     * HTML解析失败抛出异常后，输出的路径位置
     */
    public static final String ERROR_HTML_PATH = NAN_JIN_ROOT_PATH + "ERROR\\";

    public static final String NAN_JIN_IMG_PATH = NAN_JIN_ROOT_PATH + "IMG\\";


    public static final DateTimeFormatter FORMAT_FOURTEEN = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 分隔符
     */
    public static final String URL_JOIN_SPLIT = "#!#";
}
