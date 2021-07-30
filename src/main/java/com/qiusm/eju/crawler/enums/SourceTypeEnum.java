package com.qiusm.eju.crawler.enums;

/**
 * @author qiushengming
 */
public enum SourceTypeEnum {

    /**
     * 请求来源
     */
    BK("bk", "贝壳"),
    AJK("ajk", "安居客");

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
