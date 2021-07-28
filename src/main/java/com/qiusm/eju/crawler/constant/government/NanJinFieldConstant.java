package com.qiusm.eju.crawler.constant.government;

import java.util.*;

/**
 * 字段及其字段名定义
 *
 * @author qiushengming
 */
public class NanJinFieldConstant {
    /**
     * 项目名称
     */
    public static final String PROJECT_NAME = "project_name";
    /**
     * 明细URL or 下一级URL
     */
    public static final String DETAIL_URL = "detail_url";
    /**
     * 项目ID
     */
    public static final String PROJECT_ID = "project_id";
    /**
     * 最新许可证号
     */
    public static final String XKZH = "xkzh";
    /**
     * 区域
     */
    public static final String REGION = "region";
    /**
     * 最新拟定开盘时间
     */
    public static final String OPEN_TIME = "open_time";
    /**
     * 项目类别
     */
    public static final String PROJECT_CATEGORY = "project_category";
    /**
     * 销售热线
     */
    public static final String HOTLINE = "hotline";
    /**
     * 项目地址;房屋坐落地点
     */
    public static final String PROJECT_ADDRESS = "project_address";
    /**
     * 用途;房屋用途性质
     */
    public static final String HOUSE_USE = "house_use";
    /**
     * 开发企业
     */
    public static final String DEVELOPMENT_ENTERPRISE = "development_enterprise";
    /**
     * 开发企业详情页
     */
    public static final String DEVELOPMENT_ENTERPRISE_DETAIL_URL = "development_enterprise_detail_url";
    /**
     * 代理销售企业
     */
    public static final String SALES_AGENT = "sales_agent";
    /**
     * 预售许可证/现售备案证的编号;
     */
    public static final String PRE_SALE_PERMIT_CODE = "pre_sale_permit_code";
    /**
     * 预售许可证/现售备案证 ID
     * 主要用于获取现售方案
     */
    public static final String PRE_SALE_PERMIT_ID = "pre_sale_permit_id";
    /**
     * 预售许可证/现售备案证 明细URL
     */
    public static final String PRE_SALE_REG_DETAIL_URL = "pre_sale_reg_detail_url";
    /**
     * 预售方案 PDF
     */
    public static final String PRE_SALE_PLAN = "pre_sale_plan";
    /**
     * 预售许可证/现售备案证 现售方案URL 预售方案 销售方案
     */
    public static final String PRE_SALE_PLAN_URL = "pre_sale_plan_url";
    /**
     * 现售 or 预售方案名称
     */
    public static final String XS_OR_YS = "XS_OR_YS";
    /**
     * 预售证图片
     */
    public static final String PRE_SALE_LICENSE_IMG_URL = "pre_sale_license_img_url";
    /**
     * 土地使用权证/不动产权证书; 土地使用权证号/不动产权证书号
     */
    public static final String LAND_USE_RIGHT_PERMIT = "land_use_right_permit";
    /**
     * 土地使用年限
     */
    public static final String LAND_USE_PERIOD = "land_use_period";
    /**
     * 用地规划许可证
     */
    public static final String LAND_USE_PLANNING_PERMIT = "land_use_planning_permit";
    /**
     * 工程规划许可证
     */
    public static final String ENGINEERING_PLANNING_PERMIT = "engineering_planning_permit";
    /**
     * 施工许可证
     */
    public static final String CONSTRUCTION_PERMIT = "construction_permit";
    /**
     * 售房单位
     */
    public static final String SALES_UNIT = "sales_unit";
    /**
     * 销售类别
     */
    public static final String SALES_CATEGORY = "sales_category";
    /**
     * 销售方式
     */
    public static final String SALES_METHOD = "sales_method";


    /**
     * 入网总套数
     */
    public static final String TOTAL_NUMBER = "total_number";
    /**
     * 入网总面积
     */
    public static final String TOTAL_AREA = "total_area";

    /**
     * 今日认购
     */
    public static final String TODAY_SUBSCRIBE = "today_subscribe";
    /**
     * 总认购套数
     */
    public static final String SUBSCRIBE_TOTAL_NUM = "subscribe_total_num";
    /**
     * 总认购面积
     */
    public static final String SUBSCRIBE_TOTAL_AREA = "subscribe_total_area";

    /**
     * 今日成交
     */
    public static final String TODAY_TRANSACTION = "today_transaction";
    /**
     * 总成交套数
     */
    public static final String TRANSACTION_TOTAL_NUM = "transaction_total_num";
    /**
     * 总成交面积
     */
    public static final String TRANSACTION_TOTAL_AREA = "transaction_total_area";
    /**
     * 成交比例
     */
    public static final String TURNOVER_RATIO = "turnover_ratio";

    /**
     * 未售总套数
     */
    public static final String UNSOLD_TOTAL_NUM = "unsold_total_num";
    /**
     * 未售总面积
     */
    public static final String UNSOLD_TOTAL_AREA = "unsold_total_area";
    /**
     * 车库未售套数
     */
    public static final String UNSOLD_GARAGE_NUM = "unsold_garage_num";

    /**
     * 商业总均价
     */
    public static final String COMMERCIAL_TOTAL_AVERAGE_PRICE = "commercial_total_average_price";
    /**
     * 住宅总均价
     */
    public static final String HOUSE_TOTAL_AVERAGE_PRICE = "house_total_average_price";
    /**
     * 办公楼总均价
     */
    public static final String OFFICE_BUILDING_TOTAL_AVERAGE_PRICE = "office_building_total_average_price";
    /**
     * 项目总均价
     */
    public static final String PROJECT_TOTAL_AVERAGE_PRICE = "project_total_average_price";
    /**
     * 均价
     */
    public static final String AVERAGE_PRICE = "average_price";

    /**
     * 商业当月均价
     */
    public static final String COMMERCIAL_MONTH_AVERAGE_PRICE = "commercial_month_average_price";
    /**
     * 住宅当月均价
     */
    public static final String HOUSE_MONTH_AVERAGE_PRICE = "house_month_average_price";
    /**
     * 办公当月均价
     */
    public static final String OFFICE_BUILDING_MONTH_AVERAGE_PRICE = "office_building_month_average_price";

    /**
     * 物业管理公司
     */
    public static final String PROPERTY_MANAGEMENT_COMPANY = "property_management_company";
    /**
     * 施工单位
     */
    public static final String CONSTRUCTION_UNIT = "construction_unit";
    /**
     * 建筑设计单位
     */
    public static final String ARCHITECTURAL_DESIGN_UNIT = "architectural_design_unit";
    /**
     * 环艺设计单位
     */
    public static final String ENVIRONMENTAL_ART_DESIGN_UNIT = "environmental_art_design_unit";
    /**
     * 项目简介
     */
    public static final String PROJECT_DESCRIPTION = "project_description";

    /**
     * 法人名称
     */
    public static final String CORPORATE_NAME = "corporate_name";
    /**
     * 法定代表人
     */
    public static final String LEGAL_REPRESENTATIVE = "legal_representative";
    /**
     * 法人代码
     */
    public static final String LEGAL_PERSON_CODE = "legal_person_code";
    /**
     * 总经理
     */
    public static final String GENERAL_MANAGER = "general_manager";
    /**
     * 联系电话
     */
    public static final String CONTACT_NUMBER = "contact_number";
    /**
     * 营业执照号码
     */
    public static final String BUSINESS_LICENSE_NUM = "business_license_num";
    /**
     * 工商注册日
     */
    public static final String BUSINESS_REGISTRATION_DAY = "business_registration_day";
    /**
     * 执照到期日
     */
    public static final String LICENSE_EXPIRY_DATE = "license_expiry_date";
    /**
     * 注册资本
     */
    public static final String REGISTERED_CAPITAL = "registered_capital";
    /**
     * 工商注册类型
     */
    public static final String BUSINESS_REGISTRATION_TYPE = "business_registration_type";
    /**
     * 电子邮箱
     */
    public static final String EMAIL = "email";
    /**
     * 注册地址
     */
    public static final String REGISTERED_ADDRESS = "registered_address";
    /**
     * 经营地址
     */
    public static final String BUSINESS_ADDRESS = "business_address";
    /**
     * 经营范围
     */
    public static final String BUSINESS_SCOPE = "business_scope";
    /**
     * 资质证编号
     */
    public static final String QUALIFICATION_CERTIFICATE_NUMBER = "qualification_certificate_number";
    /**
     * 资质等级
     */
    public static final String QUALIFICATION_LEVEL = "qualification_level";
    /**
     * 资质发证日
     */
    public static final String QUALIFICATION_ISSUANCE_DATE = "qualification_issuance_date";
    /**
     * 批准从事日
     */
    public static final String APPROVAL_DAY = "approval_day";
    /**
     * 年检情况
     */
    public static final String ANNUAL_INSPECTION = "annual_inspection";
    /**
     * 诚信记录
     */
    public static final String INTEGRITY_RECORD = "integrity_record";
    /**
     * 曝光记录
     */
    public static final String EXPOSURE_RECORD = "exposure_record";
    /**
     * 楼栋列表
     */
    public static final String BUILDING_LIST = "building_list";
    /**
     * 楼栋号;幢号
     */
    public static final String BUILDING_NUM = "building_num";
    /**
     * 楼栋明细url
     */
    public static final String BUILDING_DETAIL_URL = "building_detail_url";
    /**
     * 物价价格申报表格
     */
    public static final String PRICE_LIST_IMG_URL = "price_list_img_url";
    /**
     * 房屋类型:一般住宅
     */
    public static final String HOUSE_TYPE = "house_type";
    /**
     * 房号
     */
    public static final String HOUSE_NUM = "house_num";
    /**
     * 房屋详情URL
     */
    public static final String HOUSE_DETAIL_URL = "house_detail_url";
    /**
     * 房屋列表
     */
    public static final String HOUSE_LIST = "house_list";
    /**
     * 房屋面积;预测建筑面积
     */
    public static final String HOUSE_AREA = "house_area";
    /**
     * 套内面积
     */
    public static final String HOUSE_SET_AREA = "house_set_area";
    /**
     * 分摊面积
     */
    public static final String HOUSE_APPORTIONED_AREA = "house_apportioned_area";
    /**
     * 房屋均价;参考价格
     */
    public static final String HOUSE_PRICE = "house_price";
    /**
     * 楼层
     */
    public static final String FLOOR = "floor";
    /**
     * 销售状态
     */
    public static final String SALES_STATUS = "sales_status";
    /**
     * 鸟瞰图URL
     */
    public static final String NIAOK_PICTURE_URL = "niaok_picture_url";
    /**
     * 区位图URL
     */
    public static final String QW_PICTURE_URL = "qw_picture_url";
    /**
     * 效果图URL
     */
    public static final String XG_PICTURE_URL = "xg_picture_url";
    /**
     * 户型图URL
     */
    public static final String LAYOUT_PICTURE_URL = "layout_picture_url";
    public static final Map<String, String> FIELD_MAPPING = new HashMap<>(128);

    static {
        // 1. 将数据分来写的主要目的，为了便于区分
        // 2. 不定义为常量是为了，让这部分内容不占用内存，数据最终存储到FIELD_MAPPING中
        // 楼盘概述
        final String[][] fieldMappingArr = {
                {"项目名称", PROJECT_NAME}, {"最新许可证号", XKZH}, {"区属", REGION}, {"最新拟开盘时间", OPEN_TIME},
                {"项目类别", PROJECT_CATEGORY}, {"销售热线", HOTLINE}, {"项目地址", PROJECT_ADDRESS}, {"用途", HOUSE_USE},
                {"开发企业", DEVELOPMENT_ENTERPRISE}, {"代理销售企业", SALES_AGENT}, {"预售许可证/现售备案证", PRE_SALE_PERMIT_CODE},
                {"土地使用权证/不动产权证书", LAND_USE_RIGHT_PERMIT}, {"用地规划许可证", LAND_USE_PLANNING_PERMIT},
                {"工程规划许可证", ENGINEERING_PLANNING_PERMIT}, {"施工许可证", CONSTRUCTION_PERMIT},
        };
        // 项目总体销售情况
        final String[][] overallProjectSales = {
                {"入网总套数", TOTAL_NUMBER},
                {"入网总面积", TOTAL_AREA},

                {"今日认购", TODAY_SUBSCRIBE},
                {"总认购套数", SUBSCRIBE_TOTAL_NUM},
                {"总认购面积", SUBSCRIBE_TOTAL_AREA},

                {"今日成交", TODAY_TRANSACTION},
                {"总成交套数", TRANSACTION_TOTAL_NUM},
                {"总成交面积", TRANSACTION_TOTAL_AREA},

                {"未售总套数", UNSOLD_TOTAL_NUM},
                {"未售总面积", UNSOLD_TOTAL_AREA},
                {"车库未售套数", UNSOLD_GARAGE_NUM},

                {"商业总均价", COMMERCIAL_TOTAL_AVERAGE_PRICE},
                {"住宅总均价", HOUSE_TOTAL_AVERAGE_PRICE},
                {"办公楼总均价", OFFICE_BUILDING_TOTAL_AVERAGE_PRICE},
                {"项目总均价", PROJECT_TOTAL_AVERAGE_PRICE},

                {"商业当月均价", COMMERCIAL_MONTH_AVERAGE_PRICE},
                {"住宅当月均价", HOUSE_MONTH_AVERAGE_PRICE},
                {"办公当月均价", OFFICE_BUILDING_MONTH_AVERAGE_PRICE},
        };
        // 项目概况
        final String[][] projectOverview = {
                {"物业管理公司", PROPERTY_MANAGEMENT_COMPANY},
                {"施工单位", CONSTRUCTION_UNIT},
                {"建筑设计单位", ARCHITECTURAL_DESIGN_UNIT},
                {"环艺设计单位", ENVIRONMENTAL_ART_DESIGN_UNIT},
                {"项目简介", PROJECT_DESCRIPTION},
        };

        // 开发企业资料
        final String[][] developmentCompanyInfo = {
                {"法人名称", CORPORATE_NAME},
                {"法定代表人", LEGAL_REPRESENTATIVE},
                {"法人代码", LEGAL_PERSON_CODE},
                {"总经理", GENERAL_MANAGER},
                {"联系电话", CONTACT_NUMBER},
                {"营业执照号码", BUSINESS_LICENSE_NUM},
                {"工商注册日", BUSINESS_REGISTRATION_DAY},
                {"执照到期日", LICENSE_EXPIRY_DATE},
                {"注册资本", REGISTERED_CAPITAL},
                {"工商注册类型", BUSINESS_REGISTRATION_TYPE},
                {"电子邮箱", EMAIL},
                {"注册地址", REGISTERED_ADDRESS},
                {"经营地址", BUSINESS_ADDRESS},
                {"经营范围", BUSINESS_SCOPE},
                {"资质证编号", QUALIFICATION_CERTIFICATE_NUMBER},
                {"资质等级", QUALIFICATION_LEVEL},
                {"资质发证日", QUALIFICATION_ISSUANCE_DATE},
                {"批准从事日", APPROVAL_DAY},
                {"年检情况", ANNUAL_INSPECTION},
                {"诚信记录", INTEGRITY_RECORD},
                {"曝光记录", EXPOSURE_RECORD},
        };

        // 预售证
        String[][] preSaleCertificate = {
                {"编号", PRE_SALE_PERMIT_CODE},
                {"区属", REGION},
                {"项目名称", PROJECT_NAME},
                {"拟开盘时间", OPEN_TIME},
                {"销售方式", SALES_METHOD},
                {"销售类别", SALES_CATEGORY},
                {"房屋坐落地点", PROJECT_ADDRESS},
                {"房屋用途性质", HOUSE_USE},
                {"售房单位", SALES_UNIT},
                {"土地使用权证号/不动产证书权号", LAND_USE_RIGHT_PERMIT},
                {"土地使用年限", LAND_USE_PERIOD}
        };

        // 房屋详情
        String[][] houseDetail = {
                {"楼层", FLOOR},
                {"房号", HOUSE_NUM},
                {"幢号", BUILDING_NUM},
                {"预测建筑面积", HOUSE_AREA},
                {"预测套内面积", HOUSE_SET_AREA},
                {"预测分摊面积", HOUSE_APPORTIONED_AREA},
                {"房屋类型", HOUSE_TYPE},
                {"销售状态", SALES_STATUS},
                {"参考价格", HOUSE_PRICE}
        };

        List<String[]> all = new ArrayList<>();
        all.addAll(Arrays.asList(fieldMappingArr));
        all.addAll(Arrays.asList(overallProjectSales));
        all.addAll(Arrays.asList(projectOverview));
        all.addAll(Arrays.asList(developmentCompanyInfo));
        all.addAll(Arrays.asList(preSaleCertificate));
        all.addAll(Arrays.asList(houseDetail));

        for (String[] strArr : all) {
            FIELD_MAPPING.put(strArr[0], strArr[1]);
        }
    }
}
