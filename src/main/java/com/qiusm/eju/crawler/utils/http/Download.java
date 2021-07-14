package com.qiusm.eju.crawler.utils.http;

import java.util.Map;

public interface Download {

    /**
     * 退出下载器
     */
    void quit();

    /**
     * GET 请求
     * 获取资源
     *
     * @param url url
     * @return html
     */
    String get(String url);

    String proxyGet(String url, Map<String, String> head);
}