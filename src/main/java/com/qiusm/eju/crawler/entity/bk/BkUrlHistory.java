package com.qiusm.eju.crawler.entity.bk;

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

    private Date createTime;

    private String classHandler;

    private String urlBase64;

    private Integer isSuccess;

    private String result;
}