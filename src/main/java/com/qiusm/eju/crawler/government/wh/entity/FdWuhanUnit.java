package com.qiusm.eju.crawler.government.wh.entity;

import java.util.Date;

public class FdWuhanUnit {
    private Long id;

    private Long houseId;

    private Long buildingId;

    private String cityName;

    private String projectId;

    private String projectName;

    private String buildingName;

    private String unitId;

    private String nominalFloor;

    private String roomNo;

    private String houseAddress;

    private String presellNo;

    private String measuredTotalArea;

    private String preBuildingAvgPrice;

    private String houseTotalPrice;

    private String houseSalesStatus;

    private String url;

    private String detailsUrl;

    private String status= "ok";

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

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getNominalFloor() {
        return nominalFloor;
    }

    public void setNominalFloor(String nominalFloor) {
        this.nominalFloor = nominalFloor == null ? null : nominalFloor.trim();
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress == null ? null : houseAddress.trim();
    }

    public String getPresellNo() {
        return presellNo;
    }

    public void setPresellNo(String presellNo) {
        this.presellNo = presellNo == null ? null : presellNo.trim();
    }

    public String getMeasuredTotalArea() {
        return measuredTotalArea;
    }

    public void setMeasuredTotalArea(String measuredTotalArea) {
        this.measuredTotalArea = measuredTotalArea == null ? null : measuredTotalArea.trim();
    }

    public String getPreBuildingAvgPrice() {
        return preBuildingAvgPrice;
    }

    public void setPreBuildingAvgPrice(String preBuildingAvgPrice) {
        this.preBuildingAvgPrice = preBuildingAvgPrice == null ? null : preBuildingAvgPrice.trim();
    }

    public String getHouseTotalPrice() {
        return houseTotalPrice;
    }

    public void setHouseTotalPrice(String houseTotalPrice) {
        this.houseTotalPrice = houseTotalPrice == null ? null : houseTotalPrice.trim();
    }

    public String getHouseSalesStatus() {
        return houseSalesStatus;
    }

    public void setHouseSalesStatus(String houseSalesStatus) {
        this.houseSalesStatus = houseSalesStatus == null ? null : houseSalesStatus.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl == null ? null : detailsUrl.trim();
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