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
@TableName("fd_wuhan_house")
@EqualsAndHashCode(callSuper = true)
public class FdWuhanHouse
        extends SuperEntity<FdWuhanHouse> {

    @TableField("city_name")
    private String cityName;
    @TableField("project_id")
    private String projectId;
    @TableField("project_name")
    private String projectName;
    @TableField("project_address")
    private String projectAddress;
    @TableField("approval_presale_house_num")
    private String approvalPresaleHouseNum;
    @TableField("house_sold_num")
    private String houseSoldNum;
    @TableField("house_unsale_num")
    private String houseUnsaleNum;
    @TableField("non_house_sold_num")
    private String nonHouseSoldNum;
    @TableField("non_house_unsold_num")
    private String nonHouseUnsoldNum;
    @TableField("contract_record_handling_department")
    private String contractRecordHandlingDepartment;
    @TableField("url")
    private String url;


    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime = new Date();

}