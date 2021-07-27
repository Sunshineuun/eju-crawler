package com.qiusm.eju.crawler.service.task.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.task.CrawlerTask;
import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.mapper.task.CrawlerTaskInstanceMapper;
import com.qiusm.eju.crawler.mapper.task.CrawlerTaskMapper;
import com.qiusm.eju.crawler.service.task.ICrawlerTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class CrawlerTaskServiceImpl
        extends ServiceImpl<CrawlerTaskMapper, CrawlerTask>
        implements ICrawlerTaskService {



}
