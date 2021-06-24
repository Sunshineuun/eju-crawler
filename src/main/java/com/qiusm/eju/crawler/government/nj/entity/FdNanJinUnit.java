package com.qiusm.eju.crawler.government.nj.entity;

import java.util.Date;

public class FdNanJinUnit {
    private Long id;

    private String cityName;

    private Long houseId;

    private Long buildingId;

    private String projectId;

    private String projectName;

    private String buildingNum;

    private String floor;

    private String houseNum;

    private String houseArea;

    private String houseSetArea;

    private String houseApportionedArea;

    private String housePrice;

    private String houseType;

    private String salesStatus;

    private String houseDetailUrl;

    private String status;

    private String version;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
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

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum == null ? null : buildingNum.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum == null ? null : houseNum.trim();
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea == null ? null : houseArea.trim();
    }

    public String getHouseSetArea() {
        return houseSetArea;
    }

    public void setHouseSetArea(String houseSetArea) {
        this.houseSetArea = houseSetArea == null ? null : houseSetArea.trim();
    }

    public String getHouseApportionedArea() {
        return houseApportionedArea;
    }

    public void setHouseApportionedArea(String houseApportionedArea) {
        this.houseApportionedArea = houseApportionedArea == null ? null : houseApportionedArea.trim();
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice == null ? null : housePrice.trim();
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    public String getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus == null ? null : salesStatus.trim();
    }

    public String getHouseDetailUrl() {
        return houseDetailUrl;
    }

    public void setHouseDetailUrl(String houseDetailUrl) {
        this.houseDetailUrl = houseDetailUrl == null ? null : houseDetailUrl.trim();
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