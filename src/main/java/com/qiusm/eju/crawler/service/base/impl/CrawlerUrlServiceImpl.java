package com.qiusm.eju.crawler.service.base.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.base.CrawlerUrl;
import com.qiusm.eju.crawler.mapper.base.CrawlerUrlMapper;
import com.qiusm.eju.crawler.service.base.ICrawlerUrlService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class CrawlerUrlServiceImpl
        extends ServiceImpl<CrawlerUrlMapper, CrawlerUrl>
        implements ICrawlerUrlService {
}
