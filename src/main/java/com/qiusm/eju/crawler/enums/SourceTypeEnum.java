package com.qiusm.eju.crawler.enums;

/**
 * @author qiushengming
 */
public enum SourceTypeEnum {

    /**
     * 请求来源
     */
    BK_APP("bk_app", "贝壳App"),
    BK_PC("bk_pc", "贝壳Pc"),
    AJK_APP("ajk_app", "安居客App"),
    GAO_DE("gao_de", "高德");

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
