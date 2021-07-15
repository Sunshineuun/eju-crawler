package com.qiusm.eju.crawler.base.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * <p>
 * 爬虫任务实例
 * </p>
 *
 * @author lz
 * @since 2018-11-28
 */
@TableName("tb_crawler_task_instance")
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class TaskInstance extends SuperEntity<TaskInstance> {

    private static final long serialVersionUID = 1L;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 任务id
     */
    @TableField("task_id")
    private Long taskId;
    /**
     * 任务类型,(beike,fangtianxia,anjuke)
     */
    private String type;
    @TableField("estimate_total")
    private Integer estimateTotal;
    /**
     * 完成总量
     */
    @TableField("complete_total")
    private Integer completeTotal;
    /**
     * 下载器配置:list[list]
     */
    @TableField("parser_config")
    private String parserConfig;

    private String name;
    /**
     * 任务资源占比
     */
    @TableField("resources_ratio")
    private Integer resourcesRatio;
    @TableField("resources_ips")
    private String resourcesIps;
    /**
     * 需要入库的解析器数据的编号
     */
    @TableField("storage_parser_ids")
    private String storageParserIds;
    /**
     * 任务完使用时长
     */
    @TableField("use_time")
    private Long useTime;
    @TableField("complete_time")
    private LocalDateTime completeTime;

    /**
     * 执行状态：0失败，1成功，2运行中，3排队中,4暂停,5中止
     */
    private Integer status;
    @TableField("batch_no")
    private String batchNo;

    @TableField("version_no")
    private Integer versionNo;

    /**
     * 编码格式
     */
    @TableField("charset")
    private String charset;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 更新人
     */
    @TableField("update_user")
    private String updateUser;
    /**
     * 数据来源
     */
    @TableField("source_type")
    private String sourceType;

    /**
     * 图片上传状态：0:全部不上传 、1：全部上传  、2：去重上传
     */
    @TableField("pic_upload_status")
    private String picUploadStatus;

    /**
     * 0:默认 1:目录名
     */
    @TableField("topic_flag")
    private String topicFlag;

    /**
     * 任务指定运行的机器ip
     */
    @TableField("appointed_ips")
    private String appointedIps;

    @TableField("mailbox")
    private String mailbox;

    @TableField("send_email")
    private Boolean sendEmail;

    /**
     * 任务开启线程数
     */
    @TableField("thread_num")
    private Integer threadNum;
    /**
     * 种子存放队列
     */
    @TableField("queue_names")
    private String queueNames;
}
