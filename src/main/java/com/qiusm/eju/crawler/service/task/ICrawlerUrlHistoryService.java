package com.qiusm.eju.crawler.service.task;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.task.CrawlerUrlHistory;

/**
 * @author qiushengming
 */
public interface ICrawlerUrlHistoryService
        extends IService<CrawlerUrlHistory> {
    CrawlerUrlHistory getByUrl(String url);

    void upHis(CrawlerUrlHistory his);
}
