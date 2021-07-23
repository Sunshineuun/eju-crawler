package com.qiusm.eju.crawler.competitor.beike.entity;

import java.util.Date;

public class BkDealUrlHistory {
    private Long id;

    private String url;

    private Date createTime;

    private String classHandler;

    private String urlBase64;

    private Integer isSuccess;

    private String result;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassHandler() {
        return classHandler;
    }

    public void setClassHandler(String classHandler) {
        this.classHandler = classHandler == null ? null : classHandler.trim();
    }

    public String getUrlBase64() {
        return urlBase64;
    }

    public void setUrlBase64(String urlBase64) {
        this.urlBase64 = urlBase64 == null ? null : urlBase64.trim();
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}