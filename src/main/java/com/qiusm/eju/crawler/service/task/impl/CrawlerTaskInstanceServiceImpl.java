package com.qiusm.eju.crawler.service.task.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.task.CrawlerTask;
import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.mapper.task.CrawlerTaskInstanceMapper;
import com.qiusm.eju.crawler.service.task.ICrawlerTaskInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class CrawlerTaskInstanceServiceImpl
        extends ServiceImpl<CrawlerTaskInstanceMapper, CrawlerTaskInstance>
        implements ICrawlerTaskInstanceService {

    @Override
    public void createInstance(CrawlerTask task) {
        // 判断创建的任务是否存在未结束的，如果存在未结束的，则提示
        EntityWrapper<CrawlerTaskInstance> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("task_id", task.getId())
                .eq("state", 99);
        long count = this.selectCount(entityWrapper);
        if (count > 0) {
            log.info("该任务已存在运行中的实例.taskId:{},count:{}", task.getId(), count);
            return;
        }

        CrawlerTaskInstance taskInstance = new CrawlerTaskInstance();
        taskInstance.setTaskId(task.getId());
        taskInstance.setCreateTime(new Date());
        taskInstance.setTaskName(task.getTaskName());
        taskInstance.setTaskParamJson(task.getTaskParamJson());
        taskInstance.setHandlerType(task.getHandlerType());
        taskInstance.setState(1);
        taskInstance.insert();
    }

    @Override
    public CrawlerTaskInstance getInstanceByHandlerType(String handlerType) {
        EntityWrapper<CrawlerTaskInstance> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("handler_type", handlerType);
        List<CrawlerTaskInstance> taskInstance = this.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(taskInstance)) {
            return taskInstance.get(0);
        }
        return null;
    }
}
