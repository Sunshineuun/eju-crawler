package com.qiusm.eju.crawler.entity.poi.gaode;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import com.qiusm.eju.crawler.entity.bk.BkUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * @author qiushengming
 */
@Data
@TableName("gaode_city_fence")
@EqualsAndHashCode(callSuper = true)
public class GaodeCityFence
        extends SuperEntity<BkUser> {

    private String name;

    private String version;

    private String fence;

    @TableField("create_time")
    private Date createTime;


}