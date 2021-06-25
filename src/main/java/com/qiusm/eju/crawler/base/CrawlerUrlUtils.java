package com.qiusm.eju.crawler.base;

import com.qiusm.eju.crawler.base.dao.CrawlerUrlMapper;
import com.qiusm.eju.crawler.base.entity.CrawlerUrl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@Component
public class CrawlerUrlUtils {

    private static CrawlerUrlUtils utils;

    @Resource
    private CrawlerUrlMapper mapper;

    @PostConstruct
    public void init() {
        utils = this;
        utils.mapper = this.mapper;
    }

    public static void saveUrl(String url, String type, String success) {
        utils.mapper.insert(new CrawlerUrl(url, type, success));
    }
}
