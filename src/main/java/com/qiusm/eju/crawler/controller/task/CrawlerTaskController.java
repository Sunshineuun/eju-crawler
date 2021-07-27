package com.qiusm.eju.crawler.controller.task;

import com.qiusm.eju.crawler.service.task.ICrawlerTaskInstanceService;
import com.qiusm.eju.crawler.service.task.ICrawlerTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 任务创建
 *
 * @author qiushengming
 */
@RestController
@RequestMapping("/crawler/task")
public class CrawlerTaskController {

    @Resource
    private ICrawlerTaskService crawlerTaskService;

    @Resource
    private ICrawlerTaskInstanceService crawlerTaskInstanceService;

    @GetMapping("/create/instance/{taskId}")
    public void createInstance(@PathVariable Integer taskId) {
        crawlerTaskInstanceService.createInstance(crawlerTaskService.selectById(taskId));
    }
}
