package com.qiusm.eju.crawler.task.entity;

public class CrawlerTask {
    private Long id;

    private String taskName;

    private String taskParamJson;

    private String handlerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}