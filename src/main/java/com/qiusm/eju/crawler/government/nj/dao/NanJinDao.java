package com.qiusm.eju.crawler.government.nj.dao;

import com.google.common.base.CaseFormat;
import com.qiusm.eju.crawler.constant.SymbolicConstant;
import com.qiusm.eju.crawler.government.nj.dao.FdNanJinBuildingMapper;
import com.qiusm.eju.crawler.government.nj.dao.FdNanJinHouseMapper;
import com.qiusm.eju.crawler.government.nj.dao.FdNanJinPreSaleMapper;
import com.qiusm.eju.crawler.government.nj.dao.FdNanJinUnitMapper;
import com.qiusm.eju.crawler.government.nj.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.government.nj.constant.NanJinConstant.CITY_NAME;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinConstant.URL_JOIN_SPLIT;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinFieldConstant.*;

/**
 * @author qiushengming
 */
@Service
public class NanJinDao {

    @Resource
    private FdNanJinHouseMapper houseMapper;

    @Resource
    private FdNanJinPreSaleMapper preSaleMapper;

    @Resource
    private FdNanJinBuildingMapper buildingMapper;

    @Resource
    private FdNanJinUnitMapper unitMapper;

    /**
     * 存储楼盘
     *
     * @param xmLpDetail 项目明细
     * @return FdNanJinHouse
     */
    public FdNanJinHouse saveXmLp(Map<String, Object> xmLpDetail) {
        FdNanJinHouseWithBLOBs house = new FdNanJinHouseWithBLOBs();
        house.setCityName(CITY_NAME);
        house.setProjectId((String) xmLpDetail.get(PROJECT_ID));
        house.setProjectName((String) xmLpDetail.get(PROJECT_NAME));
        house.setProjectAddress((String) xmLpDetail.get(PROJECT_ADDRESS));
        house.setHouseUse((String) xmLpDetail.get(HOUSE_USE));
        house.setDevelopmentEnterprise((String) xmLpDetail.get(DEVELOPMENT_ENTERPRISE));
        house.setDevelopmentEnterpriseDetailUrl((String) xmLpDetail.get(DEVELOPMENT_ENTERPRISE_DETAIL_URL));
        house.setSalesAgent((String) xmLpDetail.get(SALES_AGENT));
        house.setOpenTime((String) xmLpDetail.get(OPEN_TIME));
        house.setLandUseRightPermit((String) xmLpDetail.get(LAND_USE_RIGHT_PERMIT));
        house.setLandUsePlanningPermit((String) xmLpDetail.get(LAND_USE_PLANNING_PERMIT));
        house.setEngineeringPlanningPermit((String) xmLpDetail.get(ENGINEERING_PLANNING_PERMIT));
        house.setConstructionPermit((String) xmLpDetail.get(CONSTRUCTION_PERMIT));
        house.setTotalNumber((String) xmLpDetail.get(TOTAL_NUMBER));
        house.setTodaySubscribe((String) xmLpDetail.get(TODAY_SUBSCRIBE));
        house.setCommercialMonthAveragePrice((String) xmLpDetail.get(COMMERCIAL_MONTH_AVERAGE_PRICE));
        house.setTotalArea((String) xmLpDetail.get(TOTAL_AREA));
        house.setTodayTransaction((String) xmLpDetail.get(TODAY_TRANSACTION));
        house.setHouseTotalAveragePrice((String) xmLpDetail.get(HOUSE_TOTAL_AVERAGE_PRICE));
        house.setUnsoldTotalNum((String) xmLpDetail.get(UNSOLD_TOTAL_NUM));
        house.setSubscribeTotalNum((String) xmLpDetail.get(SUBSCRIBE_TOTAL_NUM));
        house.setUnsoldTotalArea((String) xmLpDetail.get(UNSOLD_TOTAL_AREA));
        house.setSubscribeTotalArea((String) xmLpDetail.get(SUBSCRIBE_TOTAL_AREA));
        house.setCommercialTotalAveragePrice((String) xmLpDetail.get(COMMERCIAL_TOTAL_AVERAGE_PRICE));
        house.setUnsoldGarageNum((String) xmLpDetail.get(UNSOLD_GARAGE_NUM));
        house.setTransactionTotalNum((String) xmLpDetail.get(TRANSACTION_TOTAL_NUM));
        house.setHouseMonthAveragePrice((String) xmLpDetail.get(HOUSE_MONTH_AVERAGE_PRICE));
        house.setProjectTotalAveragePrice((String) xmLpDetail.get(PROJECT_TOTAL_AVERAGE_PRICE));
        house.setTransactionTotalArea((String) xmLpDetail.get(TRANSACTION_TOTAL_AREA));
        house.setOfficeBuildingMonthAveragePrice((String) xmLpDetail.get(OFFICE_BUILDING_MONTH_AVERAGE_PRICE));
        house.setPropertyManagementCompany((String) xmLpDetail.get(PROPERTY_MANAGEMENT_COMPANY));
        house.setConstructionUnit((String) xmLpDetail.get(CONSTRUCTION_UNIT));
        house.setArchitecturalDesignUnit((String) xmLpDetail.get(ARCHITECTURAL_DESIGN_UNIT));
        house.setEnvironmentalArtDesignUnit((String) xmLpDetail.get(ENVIRONMENTAL_ART_DESIGN_UNIT));
        house.setProjectDescription((String) xmLpDetail.get(PROJECT_DESCRIPTION));
        house.setCorporateName((String) xmLpDetail.get(CORPORATE_NAME));
        house.setLegalRepresentative((String) xmLpDetail.get(LEGAL_REPRESENTATIVE));
        house.setLegalPersonCode((String) xmLpDetail.get(LEGAL_PERSON_CODE));
        house.setGeneralManager((String) xmLpDetail.get(GENERAL_MANAGER));
        house.setContactNumber((String) xmLpDetail.get(CONTACT_NUMBER));
        house.setBusinessLicenseNum((String) xmLpDetail.get(BUSINESS_LICENSE_NUM));
        house.setBusinessRegistrationDay((String) xmLpDetail.get(BUSINESS_REGISTRATION_DAY));
        house.setLicenseExpiryDate((String) xmLpDetail.get(LICENSE_EXPIRY_DATE));
        house.setRegisteredCapital((String) xmLpDetail.get(REGISTERED_CAPITAL));
        house.setBusinessRegistrationType((String) xmLpDetail.get(BUSINESS_REGISTRATION_TYPE));
        house.setEmail((String) xmLpDetail.get(EMAIL));
        house.setRegisteredAddress((String) xmLpDetail.get(REGISTERED_ADDRESS));
        house.setBusinessAddress((String) xmLpDetail.get(BUSINESS_ADDRESS));
        house.setBusinessScope((String) xmLpDetail.get(BUSINESS_SCOPE));
        house.setQualificationCertificateNumber((String) xmLpDetail.get(QUALIFICATION_CERTIFICATE_NUMBER));
        house.setQualificationLevel((String) xmLpDetail.get(QUALIFICATION_LEVEL));
        house.setQualificationIssuanceDate((String) xmLpDetail.get(QUALIFICATION_ISSUANCE_DATE));
        house.setApprovalDay((String) xmLpDetail.get(APPROVAL_DAY));
        house.setAnnualInspection((String) xmLpDetail.get(ANNUAL_INSPECTION));
        house.setIntegrityRecord((String) xmLpDetail.get(INTEGRITY_RECORD));
        house.setExposureRecord((String) xmLpDetail.get(EXPOSURE_RECORD));
        house.setNiaokPictureUrl((String) xmLpDetail.get(NIAOK_PICTURE_URL));
        house.setQwPictureUrl((String) xmLpDetail.get(QW_PICTURE_URL));

        List<String> xgList = (List<String>) xmLpDetail.get(XG_PICTURE_URL);
        house.setXgPictureUrl(String.join(URL_JOIN_SPLIT, xgList));
        List<String> layoutList = (List<String>) xmLpDetail.get(LAYOUT_PICTURE_URL);
        house.setLayoutPictureUrl(String.join(URL_JOIN_SPLIT, layoutList));

        house.setCreateTime(new Date());
        houseMapper.insert(house);
        return house;
    }

    /**
     * 存储许可证
     *
     * @param house            楼盘项目信息，主要为了取house.id
     * @param xmPreSaleDetails 预售证信息
     */
    public void saveXmPreSale(FdNanJinHouse house, List<Map<String, Object>> xmPreSaleDetails) {
        if (xmPreSaleDetails == null) {
            return;
        }

        xmPreSaleDetails.forEach(xmPreSaleDetail -> {
            FdNanJinPreSale preSale = new FdNanJinPreSale();
            preSale.setHouseId(house.getId());
            preSale.setCityName(CITY_NAME);
            preSale.setProjectId((String) xmPreSaleDetail.get(PROJECT_ID));
            preSale.setCityName((String) xmPreSaleDetail.get(CITY_NAME));
            preSale.setProjectId((String) xmPreSaleDetail.get(PROJECT_ID));
            preSale.setProjectName((String) xmPreSaleDetail.get(PROJECT_NAME));
            preSale.setPreSalePermitId((String) xmPreSaleDetail.get(PRE_SALE_PERMIT_ID));
            preSale.setPreSalePermitCode((String) xmPreSaleDetail.get(PRE_SALE_PERMIT_CODE));
            preSale.setRegion((String) xmPreSaleDetail.get(REGION));
            preSale.setOpenTime((String) xmPreSaleDetail.get(OPEN_TIME));
            preSale.setProjectAddress((String) xmPreSaleDetail.get(PROJECT_ADDRESS));
            preSale.setLandUseRightPermit((String) xmPreSaleDetail.get(LAND_USE_RIGHT_PERMIT));
            preSale.setLandUsePeriod((String) xmPreSaleDetail.get(LAND_USE_PERIOD));
            preSale.setHouseUse((String) xmPreSaleDetail.get(HOUSE_USE));
            preSale.setSalesCategory((String) xmPreSaleDetail.get(SALES_CATEGORY));
            preSale.setSalesUnit((String) xmPreSaleDetail.get(SALES_UNIT));
            preSale.setSalesMethod((String) xmPreSaleDetail.get(SALES_METHOD));
            preSale.setPreSaleLicenseImgUrl((String) xmPreSaleDetail.get(PRE_SALE_LICENSE_IMG_URL));
            preSale.setPreSaleRegDetailUrl((String) xmPreSaleDetail.get(PRE_SALE_REG_DETAIL_URL));
            preSale.setPreSalePlanUrl((String) xmPreSaleDetail.get(PRE_SALE_PLAN_URL));
            preSale.setCreateTime(new Date());
            preSaleMapper.insert(preSale);
        });

    }

    /**
     * 存储楼栋信息
     *
     * @param house          楼盘项目信息，主要为了取house.id字段
     * @param buildingDetail 楼栋信息
     * @return FdNanJinBuilding
     */
    public FdNanJinBuilding saveXmBuilding(FdNanJinHouse house, Map<String, Object> buildingDetail) {
        FdNanJinBuilding building = new FdNanJinBuilding();
        building.setHouseId(house.getId());
        building.setCityName(CITY_NAME);

        building.setProjectId(house.getProjectId());
        building.setProjectName(house.getProjectName());
        building.setBuildingNum((String) buildingDetail.get(BUILDING_NUM));
        building.setOpenTime((String) buildingDetail.get(OPEN_TIME));
        building.setTotalNumber((String) buildingDetail.get(TOTAL_NUMBER));
        building.setUnsoldTotalNum((String) buildingDetail.get(UNSOLD_TOTAL_NUM));
        building.setTodaySubscribe((String) buildingDetail.get(TODAY_SUBSCRIBE));
        building.setTodayTransaction((String) buildingDetail.get(TODAY_TRANSACTION));
        building.setSubscribeTotalNum((String) buildingDetail.get(SUBSCRIBE_TOTAL_NUM));
        building.setTransactionTotalNum((String) buildingDetail.get(TRANSACTION_TOTAL_NUM));
        building.setTransactionTotalArea((String) buildingDetail.get(TRANSACTION_TOTAL_AREA));
        building.setAveragePrice((String) buildingDetail.get(AVERAGE_PRICE));
        building.setTurnoverRatio((String) buildingDetail.get(TURNOVER_RATIO));
        building.setBuildingDetailUrl((String) buildingDetail.get(BUILDING_DETAIL_URL));
        building.setPriceListImgUrl((String) buildingDetail.get(PRICE_LIST_IMG_URL));
        building.setCreateTime(new Date());

        buildingMapper.insert(building);
        return building;
    }

    /**
     * 存储房屋信息
     *
     * @param building   楼栋信息，主要为了取house_id和building.id
     * @param unitDetail 房屋信息 转换
     */
    public void saveXmUnit(FdNanJinBuilding building, Map<String, Object> unitDetail) {
        FdNanJinUnit unit = conversionToHouseUnit(building, unitDetail);
        unit.setCreateTime(new Date());
        unitMapper.insert(unit);
    }

    public void updateXmUnit(Map<String, Object> unitDetail) {
        FdNanJinUnit unit = conversionToHouseUnit(null, unitDetail);
        unitMapper.updateByPrimaryKeySelective(unit);
    }

    private FdNanJinUnit conversionToHouseUnit(FdNanJinBuilding building, Map<String, Object> unitDetail) {
        FdNanJinUnit unit = new FdNanJinUnit();
        if (building != null) {
            unit.setHouseId(building.getHouseId());
            unit.setBuildingId(building.getId());
            unit.setProjectId(building.getProjectId());
            unit.setProjectName(building.getProjectName());
        }
        unit.setCityName(CITY_NAME);
        unit.setBuildingNum((String) unitDetail.get(BUILDING_NUM));
        unit.setFloor((String) unitDetail.get(FLOOR));
        unit.setHouseNum((String) unitDetail.get(HOUSE_NUM));
        unit.setHouseArea((String) unitDetail.get(HOUSE_AREA));
        unit.setHouseSetArea((String) unitDetail.get(HOUSE_SET_AREA));
        unit.setHouseApportionedArea((String) unitDetail.get(HOUSE_APPORTIONED_AREA));
        unit.setHousePrice((String) unitDetail.get(HOUSE_PRICE));
        unit.setHouseType((String) unitDetail.get(HOUSE_TYPE));
        unit.setSalesStatus((String) unitDetail.get(SALES_STATUS));
        unit.setHouseDetailUrl((String) unitDetail.get(HOUSE_DETAIL_URL));

        return unit;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        String a = (String) map.get("a");
        map.put("a", a);
        System.out.println();
    }

    public static void houseBuildCode() {
        String field = "id,city_name,project_id,project_name,project_address,house_use,development_enterprise,development_enterprise_detail_url,sales_agent,open_time,land_use_right_permit,land_use_planning_permit,engineering_planning_permit,construction_permit,total_number,today_subscribe,commercial_month_average_price,total_area,today_transaction,house_total_average_price,unsold_total_num,subscribe_total_num,unsold_total_area,subscribe_total_area,commercial_total_average_price,unsold_garage_num,transaction_total_num,house_month_average_price,project_total_average_price,transaction_total_area,office_building_month_average_price,property_management_company,construction_unit,architectural_design_unit,environmental_art_design_unit,project_description,corporate_name,legal_representative,legal_person_code,general_manager,contact_number,business_license_num,business_registration_day,license_expiry_date,registered_capital,business_registration_type,email,registered_address,business_address,business_scope,qualification_certificate_number,qualification_level,qualification_issuance_date,approval_day,annual_inspection,integrity_record,exposure_record,niaok_picture_url,qw_picture_url,xg_picture_url,layout_picture_url";
        String template = "house.set%s((String) xmLpDetail.get(%s));\n";
        buildCode(field, template);
    }

    public static void preSaleBuildCode() {
        String field = "id,city_name,house_id,project_id,project_name,pre_sale_permit_id,pre_sale_permit_code,region,open_time,project_address,land_use_right_permit,land_use_period,house_use,sales_category,sales_unit,sales_method,pre_sale_license_img_url,pre_sale_reg_detail_url,pre_sale_plan_url";
        String template = "preSale.set%s((String) xmPreSaleDetail.get(%s));\n";
        buildCode(field, template);
    }

    public static void buildingBuildCode() {
        String field = "project_id,project_name,building_num,open_time,total_number,unsold_total_num,today_subscribe,today_transaction,subscribe_total_num,transaction_total_num,transaction_total_area,average_price,turnover_ratio,building_detail_url,price_list_img_url";
        String template = "building.set%s((String) buildingDetail.get(%s));\n";
        buildCode(field, template);
    }

    public static void unitBuildCode() {
        String field = "project_id,project_name,building_num,floor,house_num,house_area,house_set_area,house_apportioned_area,house_price,house_type,sales_status,house_detail_url";
        String template = "unit.set%s((String) unitDetail.get(%s));\n";
        buildCode(field, template);
    }

    public static void buildCode(String field, String template) {
        for (String s : field.split(SymbolicConstant.COMMA)) {
            System.out.printf(template,
                    CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s),
                    s.toUpperCase());
        }
    }

    public List<FdNanJinHouse> selectHouseByProjectIdAndProjectName(Map<String, Object> xmLpBriefInfo) {
        if (xmLpBriefInfo.containsKey(PROJECT_ID)
                && xmLpBriefInfo.containsKey(PROJECT_NAME)) {
            FdNanJinHouseExample example = new FdNanJinHouseExample();
            example.createCriteria()
                    .andProjectIdEqualTo((String) xmLpBriefInfo.get(PROJECT_ID))
                    .andProjectNameEqualTo((String) xmLpBriefInfo.get(PROJECT_NAME));
            return houseMapper.selectByExample(example);
        } else {
            throw new NullPointerException("PROJECT_ID & PROJECT_NAME 空值");
        }
    }

    public List<FdNanJinUnit> selectHouseDetailByPendingRequest() {
        FdNanJinUnitExample example = new FdNanJinUnitExample();
        example.createCriteria().andHouseSetAreaIsNull();
        return unitMapper.selectByExample(example);
    }
}
