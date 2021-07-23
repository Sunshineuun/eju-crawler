package com.qiusm.eju.crawler.task.service;

import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstance;

/**
 * @author qiushengming
 */
public interface ICrawlerTaskService {

    /**
     * 通过任务id创建任务实例
     *
     * @param taskId 任务id
     */
    void createInstance(Integer taskId);

    CrawlerTaskInstance getInstanceByHandlerType(String handlerType);
}
