package com.qiusm.eju.crawler.poi.gaode.entity;

import java.util.Date;

public class GaodeCityFence {
    private Long id;

    private String name;

    private String version;

    private Date createTime;

    private String fence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFence() {
        return fence;
    }

    public void setFence(String fence) {
        this.fence = fence == null ? null : fence.trim();
    }
}