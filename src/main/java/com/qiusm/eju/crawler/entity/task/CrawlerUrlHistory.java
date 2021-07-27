package com.qiusm.eju.crawler.entity.task;

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
@TableName("crawler_url_history")
@EqualsAndHashCode(callSuper = true)
public class CrawlerUrlHistory
        extends SuperEntity<CrawlerUrlHistory> {

    private Long taskId;

    private String source;

    private String urlBase64;

    private String url;

    private String classHandler;

    private Integer isSuccess;

    private Date createTime;

    private String result;

}