package com.qiusm.eju.crawler.competitor.beike;

import com.qiusm.eju.crawler.EjuCrawlerApplicationTests;
import com.qiusm.eju.crawler.service.bk.ISecondHandHouseService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class SecondHandHouseTest extends EjuCrawlerApplicationTests {

    @Resource
    ISecondHandHouseService secondHandHouseService;

    @Test
    void secondHandList() {
        secondHandHouseService.handler("330100", "hz");
    }
}
