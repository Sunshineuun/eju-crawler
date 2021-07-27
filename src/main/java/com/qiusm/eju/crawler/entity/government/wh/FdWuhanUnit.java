package com.qiusm.eju.crawler.entity.government.wh;

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
@TableName("fd_wuhan_unit")
@EqualsAndHashCode(callSuper = true)
public class FdWuhanUnit
        extends SuperEntity<FdWuhanUnit> {

    @TableField("house_id")
    private Long houseId;
    @TableField("building_id")
    private Long buildingId;
    @TableField("city_name")
    private String cityName;
    @TableField("project_id")
    private String projectId;
    @TableField("project_name")
    private String projectName;
    @TableField("building_name")
    private String buildingName;
    @TableField("unit_id")
    private String unitId;
    @TableField("nominal_floor")
    private String nominalFloor;
    @TableField("room_no")
    private String roomNo;
    @TableField("house_address")
    private String houseAddress;
    @TableField("presell_no")
    private String presellNo;
    @TableField("measured_total_area")
    private String measuredTotalArea;
    @TableField("pre_building_avg_price")
    private String preBuildingAvgPrice;
    @TableField("house_total_price")
    private String houseTotalPrice;
    @TableField("house_sales_status")
    private String houseSalesStatus;
    @TableField("url")
    private String url;
    @TableField("details_url")
    private String detailsUrl;

    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime = new Date();
}