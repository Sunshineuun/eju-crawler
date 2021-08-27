package com.qiusm.eju.crawler.controller.bk;

import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.service.bk.IBkDealTaskService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Value("${eju.bk.deal.threadNum:8}")
    private Integer threadNum;
    @Resource
    private IBkDealTaskService bkDealTaskService;

    private CrawlerTaskInstance nowTask;

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
        this.nowTask = crawlerTaskInstance;
    }

    /**
     * @param cityId 城市ID
     * @param city   城市简拼
     * @param isDb   是否将结果持久化到数据库
     */
    @GetMapping("/start/{city}/{cityId}")
    public void startA(
            @PathVariable String cityId,
            @PathVariable String city,
            @RequestParam(required = false, defaultValue = "0") String isDb) {
        bkDealTaskService.scheduledTasks();
    }
}
