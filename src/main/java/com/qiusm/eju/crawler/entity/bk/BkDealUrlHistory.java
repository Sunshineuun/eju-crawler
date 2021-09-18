package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 成交请求的历史
 *
 * @author qiushengming
 */
@Data
@TableName("bk_deal_url_his1")
@EqualsAndHashCode(callSuper = true)
public class BkDealUrlHistory
        extends SuperEntity<BkDealUrlHistory> {
    private String url;
    private String classHandler;
    private String urlBase64;
    private Integer isSuccess;
    private String result;
    private Date createTime;
    private String city;
    private Long timeConsuming;
}