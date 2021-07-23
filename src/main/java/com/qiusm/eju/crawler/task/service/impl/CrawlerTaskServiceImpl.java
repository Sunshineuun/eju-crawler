package com.qiusm.eju.crawler.task.service.impl;

import com.qiusm.eju.crawler.task.dao.CrawlerTaskInstanceMapper;
import com.qiusm.eju.crawler.task.dao.CrawlerTaskMapper;
import com.qiusm.eju.crawler.task.entity.CrawlerTask;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskExample;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstance;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstanceExample;
import com.qiusm.eju.crawler.task.service.ICrawlerTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class CrawlerTaskServiceImpl
        implements ICrawlerTaskService {

    @Resource
    private CrawlerTaskMapper crawlerTaskMapper;

    @Resource
    private CrawlerTaskInstanceMapper crawlerTaskInstanceMapper;

    @Override
    public void createInstance(Integer taskId) {

        // 判断创建的任务是否存在未结束的，如果存在未结束的，则提示
        CrawlerTaskInstanceExample example = new CrawlerTaskInstanceExample();
        example.createCriteria()
                .andTaskIdEqualTo(Long.valueOf(taskId))
                .andStateNotEqualTo(99);
        long count = crawlerTaskInstanceMapper.countByExample(example);
        if (count > 0) {
            log.info("该任务已存在运行中的实例.taskId:{},count:{}", taskId, count);
            return;
        }

        CrawlerTask task = crawlerTaskMapper.selectByPrimaryKey(Long.valueOf(taskId));

        CrawlerTaskInstance taskInstance = new CrawlerTaskInstance();
        taskInstance.setTaskId(task.getId());
        taskInstance.setCreateTime(new Date());
        taskInstance.setTaskName(task.getTaskName());
        taskInstance.setTaskParamJson(task.getTaskParamJson());
        taskInstance.setHandlerType(task.getHandlerType());
        taskInstance.setState(1);
        crawlerTaskInstanceMapper.insert(taskInstance);
    }

    @Override
    public CrawlerTaskInstance getInstanceByHandlerType(String handlerType) {
        CrawlerTaskInstanceExample example = new CrawlerTaskInstanceExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria()
                .andHandlerTypeEqualTo(handlerType)
                .andStateNotEqualTo(99);
        List<CrawlerTaskInstance> taskInstance = crawlerTaskInstanceMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(taskInstance)) {
            return taskInstance.get(0);
        }
        return null;
    }
}
