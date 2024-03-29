package com.qiusm.eju.crawler.enums;


/**
 * httpclient请求的类型定义，不同类型请求方式不同。
 * {@link DownloadThread#run()}
 *
 * @author qiusm
 */
public enum RequestMethodEnum {
    /**
     * GET;POST_FORM;POST_JSON
     */
    GET("get"),
    PROXY_GET("proxy-get"),
    POST_FORM("post-form"),
    PROXY_POST_JSON("proxy-post-json"),
    PROXY_POST_FORM("proxy_-post-form"),
    CHROME_DRIVE("chrome-drive"),
    IMG("img");

    private final String code;

    RequestMethodEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
