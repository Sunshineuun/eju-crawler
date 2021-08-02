package com.qiusm.eju.crawler.enums.poi;

public enum LocationKeyEnum {

    /**
     * 地图坐标类型
     */
    GAO_DE("gaode", "高德"),
    BAI_DU("baidu", "百度");

    private final String code;
    private final String des;

    LocationKeyEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }


    public String getDes() {
        return des;
    }

    public String getCode() {
        return code;
    }

}