package com.qiusm.eju.crawler.enums;

/**
 * 小区抓取任务状态
 *
 * @author qiushengming
 */
public enum CommunitySkeletonTaskStateEnum {
    /**
     * HTTP状态码
     */
    INIT("0", "初始化"),
    RUNNING("1", "运行中"),
    EXCEPTION("2", "抓取失败"),
    COMPLETE("3", "抓取完毕"),
    PUSH_KAFKA("4", "推送数据完毕");


    private String code;
    private String msg;

    CommunitySkeletonTaskStateEnum(String code, String msg) {
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
        return String.valueOf(code);
    }
}
