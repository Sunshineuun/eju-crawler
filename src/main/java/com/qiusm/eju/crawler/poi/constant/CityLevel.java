package com.qiusm.eju.crawler.poi.constant;

/**
 * 城市级别
 *
 * @author qiushengming
 */
public enum CityLevel {
    /**
     * 城市区域级别
     */
    CITY("CITY", "城市"),
    COUNTRY("COUNTRY", "国家"),
    DISTRICT("DISTRICT", "区"),
    PROVINCE("PROVINCE", "省"),
    STREET("STREET", "街道");

    private String code;
    private String msg;

    CityLevel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
