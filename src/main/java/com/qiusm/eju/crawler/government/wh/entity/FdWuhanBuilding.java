package com.qiusm.eju.crawler.government.wh.entity;

import java.util.Date;

public class FdWuhanBuilding {
    private Long id;

    private Long houseId;

    private String cityName;

    private String projectId;

    private String projectName;

    private String buildingId;

    private String buildingName;

    private String buildingTotalLayerNum;

    private String buildingHouseNum;

    private String buildingArea;

    private String surveyingMappingAgencies;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getBuildingTotalLayerNum() {
        return buildingTotalLayerNum;
    }

    public void setBuildingTotalLayerNum(String buildingTotalLayerNum) {
        this.buildingTotalLayerNum = buildingTotalLayerNum == null ? null : buildingTotalLayerNum.trim();
    }

    public String getBuildingHouseNum() {
        return buildingHouseNum;
    }

    public void setBuildingHouseNum(String buildingHouseNum) {
        this.buildingHouseNum = buildingHouseNum == null ? null : buildingHouseNum.trim();
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea == null ? null : buildingArea.trim();
    }

    public String getSurveyingMappingAgencies() {
        return surveyingMappingAgencies;
    }

    public void setSurveyingMappingAgencies(String surveyingMappingAgencies) {
        this.surveyingMappingAgencies = surveyingMappingAgencies == null ? null : surveyingMappingAgencies.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 目前的情况是：第一个URL是，楼盘列表的地址；第二个URL是，楼盘明细的地址（房屋信息）
     *
     * @param url
     */
    public void addUrl(String url) {
        if (this.url == null) {
            this.url = url == null ? null : url.trim();
        } else {
            this.url += url == null ? "" : ";" + url.trim();
        }
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