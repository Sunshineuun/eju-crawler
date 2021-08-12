package com.qiusm.eju.crawler.entity.bk;

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
@TableName("bk_url_history")
@EqualsAndHashCode(callSuper = true)
public class BkUrlHistory
        extends SuperEntity<BkUrlHistory> {
    private String url;

    @TableField("class_handler")
    private String classHandler;

    @TableField("url_base64")
    private String urlBase64;

    @TableField("is_success")
    private Integer isSuccess;

    private String result;

    @TableField("create_time")
    private Date createTime;

    private String city;
}