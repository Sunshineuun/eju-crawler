package com.qiusm.eju.crawler.base.entity;

import java.util.Date;

public class CrawlerUrl {
    private Long id;

    private String url;

    private String type;

    private String success;

    private Date createTime;

    public CrawlerUrl() {

    }

    public CrawlerUrl(String requestUrl, String type, String success) {
        this.url = requestUrl;
        this.type = type;
        this.success = success;
        this.createTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}