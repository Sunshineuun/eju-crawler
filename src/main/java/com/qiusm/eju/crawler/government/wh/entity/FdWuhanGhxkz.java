package com.qiusm.eju.crawler.government.wh.entity;

import java.util.Date;

public class FdWuhanGhxkz {
    private Long id;

    private Long houseId;

    private String cityName;

    private String projectId;

    private String ghxkzConstructionUnit;

    private String ghxkzNo;

    private String builndingNum;

    private String baseArea;

    private String householdsNum;

    private String url;

    private String status = "ok";

    private String version;

    private Date createTime = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getGhxkzConstructionUnit() {
        return ghxkzConstructionUnit;
    }

    public void setGhxkzConstructionUnit(String ghxkzConstructionUnit) {
        this.ghxkzConstructionUnit = ghxkzConstructionUnit == null ? null : ghxkzConstructionUnit.trim();
    }

    public String getGhxkzNo() {
        return ghxkzNo;
    }

    public void setGhxkzNo(String ghxkzNo) {
        this.ghxkzNo = ghxkzNo == null ? null : ghxkzNo.trim();
    }

    public String getBuilndingNum() {
        return builndingNum;
    }

    public void setBuilndingNum(String builndingNum) {
        this.builndingNum = builndingNum == null ? null : builndingNum.trim();
    }

    public String getBaseArea() {
        return baseArea;
    }

    public void setBaseArea(String baseArea) {
        this.baseArea = baseArea == null ? null : baseArea.trim();
    }

    public String getHouseholdsNum() {
        return householdsNum;
    }

    public void setHouseholdsNum(String householdsNum) {
        this.householdsNum = householdsNum == null ? null : householdsNum.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}