package com.qiusm.eju.crawler.government.wh.entity;

import java.util.Date;

public class FdWuhanHouse {
    private Long id;

    private String cityName;

    private String projectId;

    private String projectName;

    private String projectAddress;

    private String approvalPresaleHouseNum;

    private String houseSoldNum;

    private String houseUnsaleNum;

    private String nonHouseSoldNum;

    private String nonHouseUnsoldNum;

    private String contractRecordHandlingDepartment;

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

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress == null ? null : projectAddress.trim();
    }

    public String getApprovalPresaleHouseNum() {
        return approvalPresaleHouseNum;
    }

    public void setApprovalPresaleHouseNum(String approvalPresaleHouseNum) {
        this.approvalPresaleHouseNum = approvalPresaleHouseNum == null ? null : approvalPresaleHouseNum.trim();
    }

    public String getHouseSoldNum() {
        return houseSoldNum;
    }

    public void setHouseSoldNum(String houseSoldNum) {
        this.houseSoldNum = houseSoldNum == null ? null : houseSoldNum.trim();
    }

    public String getHouseUnsaleNum() {
        return houseUnsaleNum;
    }

    public void setHouseUnsaleNum(String houseUnsaleNum) {
        this.houseUnsaleNum = houseUnsaleNum == null ? null : houseUnsaleNum.trim();
    }

    public String getNonHouseSoldNum() {
        return nonHouseSoldNum;
    }

    public void setNonHouseSoldNum(String nonHouseSoldNum) {
        this.nonHouseSoldNum = nonHouseSoldNum == null ? null : nonHouseSoldNum.trim();
    }

    public String getNonHouseUnsoldNum() {
        return nonHouseUnsoldNum;
    }

    public void setNonHouseUnsoldNum(String nonHouseUnsoldNum) {
        this.nonHouseUnsoldNum = nonHouseUnsoldNum == null ? null : nonHouseUnsoldNum.trim();
    }

    public String getContractRecordHandlingDepartment() {
        return contractRecordHandlingDepartment;
    }

    public void setContractRecordHandlingDepartment(String contractRecordHandlingDepartment) {
        this.contractRecordHandlingDepartment = contractRecordHandlingDepartment == null ? null : contractRecordHandlingDepartment.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (null != this.url) {
            this.url += "," + (url == null ? null : url.trim());
        } else {
            this.url = url == null ? null : url.trim();
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