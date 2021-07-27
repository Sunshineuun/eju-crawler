package com.qiusm.eju.crawler.entity.task;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.CrawlerUrl;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qiushengming
 */
@Data
@TableName("crawler_task")
@EqualsAndHashCode(callSuper = true)
public class CrawlerTask
        extends SuperEntity<CrawlerUrl> {

    @TableField("task_name")
    private String taskName;

    @TableField("task_param_json")
    private String taskParamJson;

    @TableField("handler_type")
    private String handlerType;
}