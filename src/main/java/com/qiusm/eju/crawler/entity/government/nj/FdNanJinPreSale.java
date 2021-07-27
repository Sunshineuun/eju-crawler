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
@TableName("fd_nanjin_pre_sale")
@EqualsAndHashCode(callSuper = true)
public class FdNanJinPreSale
        extends SuperEntity<FdNanJinPreSale> {
    @TableField("city_name")
    private String cityName;
    @TableField("house_id")
    private Long houseId;
    @TableField("project_id")
    private String projectId;
    @TableField("project_name")
    private String projectName;
    @TableField("pre_sale_permit_id")
    private String preSalePermitId;
    @TableField("pre_sale_permit_code")
    private String preSalePermitCode;
    @TableField("region")
    private String region;
    @TableField("open_time")
    private String openTime;
    @TableField("project_address")
    private String projectAddress;
    @TableField("land_use_right_permit")
    private String landUseRightPermit;
    @TableField("land_use_period")
    private String landUsePeriod;
    @TableField("house_use")
    private String houseUse;
    @TableField("sales_category")
    private String salesCategory;
    @TableField("sales_unit")
    private String salesUnit;
    @TableField("sales_method")
    private String salesMethod;
    @TableField("pre_sale_license_img_url")
    private String preSaleLicenseImgUrl;
    @TableField("pre_sale_reg_detail_url")
    private String preSaleRegDetailUrl;
    @TableField("pre_sale_plan_url")
    private String preSalePlanUrl;
    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime;

}