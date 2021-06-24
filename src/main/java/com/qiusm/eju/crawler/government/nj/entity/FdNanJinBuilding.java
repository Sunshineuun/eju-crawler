package com.qiusm.eju.crawler.government.nj.entity;

import java.util.Date;

public class FdNanJinBuilding {
    private Long id;

    private String cityName;

    private Long houseId;

    private String projectId;

    private String projectName;

    private String buildingNum;

    private String openTime;

    private String totalNumber;

    private String unsoldTotalNum;

    private String todaySubscribe;

    private String todayTransaction;

    private String subscribeTotalNum;

    private String transactionTotalNum;

    private String transactionTotalArea;

    private String averagePrice;

    private String turnoverRatio;

    private String buildingDetailUrl;

    private String priceListImgUrl;

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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber == null ? null : totalNumber.trim();
    }

    public String getUnsoldTotalNum() {
        return unsoldTotalNum;
    }

    public void setUnsoldTotalNum(String unsoldTotalNum) {
        this.unsoldTotalNum = unsoldTotalNum == null ? null : unsoldTotalNum.trim();
    }

    public String getTodaySubscribe() {
        return todaySubscribe;
    }

    public void setTodaySubscribe(String todaySubscribe) {
        this.todaySubscribe = todaySubscribe == null ? null : todaySubscribe.trim();
    }

    public String getTodayTransaction() {
        return todayTransaction;
    }

    public void setTodayTransaction(String todayTransaction) {
        this.todayTransaction = todayTransaction == null ? null : todayTransaction.trim();
    }

    public String getSubscribeTotalNum() {
        return subscribeTotalNum;
    }

    public void setSubscribeTotalNum(String subscribeTotalNum) {
        this.subscribeTotalNum = subscribeTotalNum == null ? null : subscribeTotalNum.trim();
    }

    public String getTransactionTotalNum() {
        return transactionTotalNum;
    }

    public void setTransactionTotalNum(String transactionTotalNum) {
        this.transactionTotalNum = transactionTotalNum == null ? null : transactionTotalNum.trim();
    }

    public String getTransactionTotalArea() {
        return transactionTotalArea;
    }

    public void setTransactionTotalArea(String transactionTotalArea) {
        this.transactionTotalArea = transactionTotalArea == null ? null : transactionTotalArea.trim();
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice == null ? null : averagePrice.trim();
    }

    public String getTurnoverRatio() {
        return turnoverRatio;
    }

    public void setTurnoverRatio(String turnoverRatio) {
        this.turnoverRatio = turnoverRatio == null ? null : turnoverRatio.trim();
    }

    public String getBuildingDetailUrl() {
        return buildingDetailUrl;
    }

    public void setBuildingDetailUrl(String buildingDetailUrl) {
        this.buildingDetailUrl = buildingDetailUrl == null ? null : buildingDetailUrl.trim();
    }

    public String getPriceListImgUrl() {
        return priceListImgUrl;
    }

    public void setPriceListImgUrl(String priceListImgUrl) {
        this.priceListImgUrl = priceListImgUrl == null ? null : priceListImgUrl.trim();
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