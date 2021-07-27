package com.qiusm.eju.crawler.controller.base;

import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.service.task.ICrawlerTaskInstanceService;
import com.qiusm.eju.crawler.service.task.ICrawlerTaskService;
import com.qiusm.eju.crawler.utils.ThreadUtils;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
public abstract class CrawlerTaskSchedulingBaseController {

    @Resource
    protected ICrawlerTaskInstanceService crawlerTaskInstanceService;

    /**
     * 任务调度
     */
    public void scheduling() {
        Thread taskSchedulingThread = new Thread(() -> {
            while (true) {
                CrawlerTaskInstance var = crawlerTaskInstanceService.getInstanceByHandlerType(this.getClass().getSimpleName());
                if (var != null) {
                    start(var);
                }
                ThreadUtils.sleep(60);
            }
        });
        taskSchedulingThread.setName(this.getClass().getSimpleName() + "task-scheduling");
        taskSchedulingThread.start();
    }

    /**
     * 子类启动的方法
     *
     * @param crawlerTaskInstance 任务实例
     */
    public abstract void start(CrawlerTaskInstance crawlerTaskInstance);
}
