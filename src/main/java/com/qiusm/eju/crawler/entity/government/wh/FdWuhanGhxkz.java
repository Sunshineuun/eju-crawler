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
@TableName("fd_wuhan_ghxkz")
@EqualsAndHashCode(callSuper = true)
public class FdWuhanGhxkz
        extends SuperEntity<FdWuhanGhxkz> {
    @TableField("house_id")
    private Long houseId;
    @TableField("city_name")
    private String cityName;
    @TableField("project_id")
    private String projectId;
    @TableField("ghxkz_construction_unit")
    private String ghxkzConstructionUnit;
    @TableField("ghxkz_no")
    private String ghxkzNo;
    @TableField("builnding_num")
    private String builndingNum;
    @TableField("base_area")
    private String baseArea;
    @TableField("households_num")
    private String householdsNum;
    @TableField("url")
    private String url;
    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime;
}