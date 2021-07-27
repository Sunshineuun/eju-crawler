package com.qiusm.eju.crawler.entity.task;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.CrawlerUrl;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author qiushengming
 */
@Data
@TableName("crawler_task_instance")
@EqualsAndHashCode(callSuper = true)
public class CrawlerTaskInstance
        extends SuperEntity<CrawlerUrl> {

    @TableField("task_id")
    private Long taskId;

    @TableField("task_name")
    private String taskName;

    @TableField("task_param_json")
    private String taskParamJson;

    @TableField("handler_type")
    private String handlerType;

    @TableField("handle_request_num")
    private Long handleRequestNum;

    @TableField("handle_request_failure_num")
    private Long handleRequestFailureNum;

    @TableField("handle_request_success_num")
    private Long handleRequestSuccessNum;

    private Integer state;

    @TableField("create_time")
    private Date createTime;

}