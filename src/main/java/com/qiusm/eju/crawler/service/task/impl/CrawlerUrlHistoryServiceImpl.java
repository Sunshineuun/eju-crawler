package com.qiusm.eju.crawler.service.task.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.task.CrawlerUrlHistory;
import com.qiusm.eju.crawler.mapper.task.CrawlerUrlHistoryMapper;
import com.qiusm.eju.crawler.service.task.ICrawlerUrlHistoryService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class CrawlerUrlHistoryServiceImpl
        extends ServiceImpl<CrawlerUrlHistoryMapper, CrawlerUrlHistory>
        implements ICrawlerUrlHistoryService {
}
