package com.qiusm.eju.crawler.controller.bk;

import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.service.bk.IBkDealTaskService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 成交
 *
 * @author qiushengming
 */
@Api(tags = {"BeikeDeal", "bk成交数据"})
@Slf4j
@RestController
@RequestMapping("/bk/deal")
public class BeikeDealController extends BeiKeBaseController {
    @Resource
    private IBkDealTaskService bkDealTaskService;

    private CrawlerTaskInstance nowTask;

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
        this.nowTask = crawlerTaskInstance;
    }

    /**
     * 开始执行任务，任务配置在表中
     */
    @GetMapping("/start/task")
    public void startTask() {
        bkDealTaskService.scheduledTasks();
    }

    /**
     * 将数据解析到db中
     */
    @GetMapping("/start/toDb")
    public void startToDb() {
        bkDealTaskService.toDb();
    }
}
