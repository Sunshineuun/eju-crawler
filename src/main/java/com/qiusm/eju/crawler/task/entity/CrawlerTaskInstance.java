package com.qiusm.eju.crawler.task.entity;

import java.util.Date;

public class CrawlerTaskInstance {
    private Long id;

    private Long taskId;

    private String taskName;

    private String taskParamJson;

    private String handlerType;

    private Long handleRequestNum;

    private Long handleRequestFailureNum;

    private Long handleRequestSuccessNum;

    private Integer state;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskParamJson() {
        return taskParamJson;
    }

    public void setTaskParamJson(String taskParamJson) {
        this.taskParamJson = taskParamJson == null ? null : taskParamJson.trim();
    }

    public String getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(String handlerType) {
        this.handlerType = handlerType == null ? null : handlerType.trim();
    }

    public Long getHandleRequestNum() {
        return handleRequestNum;
    }

    public void setHandleRequestNum(Long handleRequestNum) {
        this.handleRequestNum = handleRequestNum;
    }

    public Long getHandleRequestFailureNum() {
        return handleRequestFailureNum;
    }

    public void setHandleRequestFailureNum(Long handleRequestFailureNum) {
        this.handleRequestFailureNum = handleRequestFailureNum;
    }

    public Long getHandleRequestSuccessNum() {
        return handleRequestSuccessNum;
    }

    public void setHandleRequestSuccessNum(Long handleRequestSuccessNum) {
        this.handleRequestSuccessNum = handleRequestSuccessNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}