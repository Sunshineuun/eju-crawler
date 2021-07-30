package com.qiusm.eju.crawler.entity.ajk;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author qiushengming
 */
@Data
@TableName(value = "ajk_url_history")
@EqualsAndHashCode(callSuper = true)
public class AjkUrlHistory
        extends SuperEntity<AjkUrlHistory> {
    private String url;

    @TableField(value = "params")
    private String params;

    @TableField("class_handler")
    private String classHandler;

    @TableField("url_base64")
    private String urlBase64;

    @TableField("is_success")
    private Integer isSuccess;

    private String result;

    @TableField("create_time")
    private Date createTime;
}