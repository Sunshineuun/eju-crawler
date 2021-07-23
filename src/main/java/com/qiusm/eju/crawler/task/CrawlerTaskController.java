package com.qiusm.eju.crawler.task;

import com.qiusm.eju.crawler.task.service.ICrawlerTaskService;
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
    ICrawlerTaskService crawlerTaskService;

    @GetMapping("/create/instance/{taskId}")
    public void createInstance(@PathVariable Integer taskId) {
        crawlerTaskService.createInstance(taskId);
    }
}
