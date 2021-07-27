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
@TableName("fd_wuhan_building")
@EqualsAndHashCode(callSuper = true)
public class FdWuhanBuilding
        extends SuperEntity<FdWuhanBuilding> {
    @TableField("house_id")
    private Long houseId;
    @TableField("city_name")
    private String cityName;
    @TableField("project_id")
    private String projectId;
    @TableField("project_name")
    private String projectName;
    @TableField("building_id")
    private String buildingId;
    @TableField("building_name")
    private String buildingName;
    @TableField("building_total_layer_num")
    private String buildingTotalLayerNum;
    @TableField("building_house_num")
    private String buildingHouseNum;
    @TableField("building_area")
    private String buildingArea;
    @TableField("surveying_mapping_agencies")
    private String surveyingMappingAgencies;
    @TableField("url")
    private String url;
    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime = new Date();

}