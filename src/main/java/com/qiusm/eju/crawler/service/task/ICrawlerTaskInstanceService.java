package com.qiusm.eju.crawler.service.task;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.task.CrawlerTask;
import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;

/**
 * @author qiushengming
 */
public interface ICrawlerTaskInstanceService
        extends IService<CrawlerTaskInstance> {

    void createInstance(CrawlerTask taskId);

    CrawlerTaskInstance getInstanceByHandlerType(String handlerType);
}
