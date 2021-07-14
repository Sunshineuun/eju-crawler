package com.qiusm.eju.crawler.constant.enums;

/**
 * @author qiushengming
 */
public enum BkErrorCodeEnum {
    /**
     * bk错误定义
     */
    BK10001("10001", "系统错误，请稍后再试"),
    BK20002("20002", "The verify code field is required.(20002-45N)"),
    BK20004("20004", "无效的请求(20004-QZM)"),
    BK20021("20021", "请输入正确的图片验证码(20021-BSK"),
    BK20026("20026", "图片验证码错误(20026-PG4)"),
    BK30020("30020", "发送短信验证码失败(30020-7WA)");

    private final String code;

    private final String msg;

    BkErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
