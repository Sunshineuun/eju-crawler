package com.qiusm.eju.crawler.entity.government.nj;

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
@TableName("fd_nanjin_unit")
@EqualsAndHashCode(callSuper = true)
public class FdNanJinUnit
        extends SuperEntity<FdNanJinUnit> {

    @TableField("city_name")
    private String cityName;

    @TableField("house_id")
    private Long houseId;

    @TableField("building_id")
    private Long buildingId;

    @TableField("project_id")
    private String projectId;

    @TableField("project_name")
    private String projectName;

    @TableField("building_num")
    private String buildingNum;

    private String floor;

    @TableField("house_num")
    private String houseNum;

    @TableField("house_area")
    private String houseArea;

    @TableField("house_set_area")
    private String houseSetArea;

    @TableField("house_apportioned_area")
    private String houseApportionedArea;

    @TableField("house_price")
    private String housePrice;

    @TableField("house_type")
    private String houseType;

    @TableField("sales_status")
    private String salesStatus;

    @TableField("house_detail_url")
    private String houseDetailUrl;

    private String status;

    private String version;

    @TableField("create_time")
    private Date createTime;

}