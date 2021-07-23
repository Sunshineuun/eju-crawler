package com.qiusm.eju.crawler.base;

import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstance;
import com.qiusm.eju.crawler.task.service.ICrawlerTaskService;
import com.qiusm.eju.crawler.utils.ThreadUtils;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
public abstract class CrawlerTaskSchedulingBaseController {

    @Resource
    protected ICrawlerTaskService crawlerTaskService;

    /**
     * 任务调度
     */
    public void scheduling() {
        Thread taskSchedulingThread = new Thread(() -> {
            while (true) {
                CrawlerTaskInstance var = crawlerTaskService.getInstanceByHandlerType(this.getClass().getSimpleName());
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
