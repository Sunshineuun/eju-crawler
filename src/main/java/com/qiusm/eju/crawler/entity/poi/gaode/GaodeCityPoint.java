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
@TableName("gaode_city_point")
@EqualsAndHashCode(callSuper = true)
public class GaodeCityPoint
        extends SuperEntity<GaodeCityPoint> {

    @TableField("city_name")
    private String cityName;

    private String longitude;

    private String latitude;

    private String version;

    private String status;

    @TableField("create_time")
    private Date createTime;
}