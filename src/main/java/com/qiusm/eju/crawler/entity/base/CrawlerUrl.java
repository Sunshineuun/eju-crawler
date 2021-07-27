package com.qiusm.eju.crawler.entity.base;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiushengming
 */
@Data
@TableName("crawler_url")
@EqualsAndHashCode(callSuper = true)
public class CrawlerUrl
        extends SuperEntity<CrawlerUrl>
        implements Serializable {
    private Long id;

    private String url;

    private String type;

    private String success;

    private Date createTime;

    public CrawlerUrl() {

    }

    public CrawlerUrl(String requestUrl, String type, String success) {
        this.url = requestUrl;
        this.type = type;
        this.success = success;
        this.createTime = new Date();
    }

}