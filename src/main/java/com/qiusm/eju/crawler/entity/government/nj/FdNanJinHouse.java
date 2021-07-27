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
@TableName("fd_nanjin_house")
@EqualsAndHashCode(callSuper = true)
public class FdNanJinHouse
        extends SuperEntity<FdNanJinHouse> {
    @TableField("city_name")
    private String cityName;
    @TableField("project_id")
    private String projectId;
    @TableField("project_name")
    private String projectName;
    @TableField("project_address")
    private String projectAddress;
    @TableField("house_use")
    private String houseUse;
    @TableField("development_enterprise")
    private String developmentEnterprise;
    @TableField("development_enterprise_detail_url")
    private String developmentEnterpriseDetailUrl;
    @TableField("sales_agent")
    private String salesAgent;
    @TableField("open_time")
    private String openTime;
    @TableField("land_use_right_permit")
    private String landUseRightPermit;
    @TableField("land_use_planning_permit")
    private String landUsePlanningPermit;
    @TableField("engineering_planning_permit")
    private String engineeringPlanningPermit;
    @TableField("construction_permit")
    private String constructionPermit;
    @TableField("total_number")
    private String totalNumber;
    @TableField("today_subscribe")
    private String todaySubscribe;
    @TableField("commercial_month_average_price")
    private String commercialMonthAveragePrice;
    @TableField("total_area")
    private String totalArea;
    @TableField("today_transaction")
    private String todayTransaction;
    @TableField("house_total_average_price")
    private String houseTotalAveragePrice;
    @TableField("unsold_total_num")
    private String unsoldTotalNum;
    @TableField("subscribe_total_num")
    private String subscribeTotalNum;
    @TableField("unsold_total_area")
    private String unsoldTotalArea;
    @TableField("subscribe_total_area")
    private String subscribeTotalArea;
    @TableField("commercial_total_average_price")
    private String commercialTotalAveragePrice;
    @TableField("unsold_garage_num")
    private String unsoldGarageNum;
    @TableField("transaction_total_num")
    private String transactionTotalNum;
    @TableField("house_month_average_price")
    private String houseMonthAveragePrice;
    @TableField("project_total_average_price")
    private String projectTotalAveragePrice;
    @TableField("transaction_total_area")
    private String transactionTotalArea;
    @TableField("office_building_month_average_price")
    private String officeBuildingMonthAveragePrice;
    @TableField("property_management_company")
    private String propertyManagementCompany;
    @TableField("construction_unit")
    private String constructionUnit;
    @TableField("architectural_design_unit")
    private String architecturalDesignUnit;
    @TableField("environmental_art_design_unit")
    private String environmentalArtDesignUnit;
    @TableField("project_description")
    private String projectDescription;
    @TableField("corporate_name")
    private String corporateName;
    @TableField("legal_representative")
    private String legalRepresentative;
    @TableField("legal_person_code")
    private String legalPersonCode;
    @TableField("general_manager")
    private String generalManager;
    @TableField("contact_number")
    private String contactNumber;
    @TableField("business_license_num")
    private String businessLicenseNum;
    @TableField("business_registration_day")
    private String businessRegistrationDay;
    @TableField("license_expiry_date")
    private String licenseExpiryDate;
    @TableField("registered_capital")
    private String registeredCapital;
    @TableField("business_registration_type")
    private String businessRegistrationType;
    @TableField("email")
    private String email;
    @TableField("registered_address")
    private String registeredAddress;
    @TableField("business_address")
    private String businessAddress;
    @TableField("business_scope")
    private String businessScope;
    @TableField("qualification_certificate_number")
    private String qualificationCertificateNumber;
    @TableField("qualification_level")
    private String qualificationLevel;
    @TableField("qualification_issuance_date")
    private String qualificationIssuanceDate;
    @TableField("approval_day")
    private String approvalDay;
    @TableField("annual_inspection")
    private String annualInspection;
    @TableField("integrity_record")
    private String integrityRecord;
    @TableField("exposure_record")
    private String exposureRecord;
    @TableField("niaok_picture_url")
    private String niaokPictureUrl;
    @TableField("qw_picture_url")
    private String qwPictureUrl;
    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime;
    @TableField("xg_picture_url")
    private String xgPictureUrl;
    @TableField("layout_picture_url")
    private String layoutPictureUrl;
}