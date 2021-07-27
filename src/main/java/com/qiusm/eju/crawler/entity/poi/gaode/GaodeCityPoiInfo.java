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
@TableName("gaode_city_poi_info")
@EqualsAndHashCode(callSuper = true)
public class GaodeCityPoiInfo extends SuperEntity<BkUser> {

    @TableField("city_code")
    private String cityCode;

    @TableField("parent_code")
    private String parentCode;

    @TableField("ad_code")
    private String adCode;

    private String name;

    private String level;

    private String center;

    @TableField("fence_id")
    private Long fenceId;

    private String status;

    private String version;

    @TableField("create_time")
    private Date createTime;
}