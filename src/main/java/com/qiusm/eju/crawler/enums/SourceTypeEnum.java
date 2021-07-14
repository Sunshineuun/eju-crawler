package com.qiusm.eju.crawler.enums;

/**
 * @author qiushengming
 */
public enum SourceTypeEnum {

    /**
     * 请求来源
     */
    SOURCE_00("0", "url"),
    SOURCE_01("1", "datasource");

    private final String code;
    private final String des;

    SourceTypeEnum(String code, String des) {
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
