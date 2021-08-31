package com.qiusm.eju.crawler.entity.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author qiushengming
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("community_detail")
public class CommunityDetail extends SuperEntity<CommunityDetail> {

    private static final long serialVersionUID = 4533019598863655509L;
    /**
     * 城市名称
     */
    @JsonProperty("city")
    @TableField("city")
    private String city;

    /**
     * 城市编码
     */
    @JsonProperty("city_code")
    @TableField("city_code")
    private String cityCode;

    /**
     * 区域
     */
    @JsonProperty("region")
    @TableField("region")
    private String region;

    /**
     * 区域编码
     */
    @JsonProperty("region_code")
    @TableField("region_code")
    private String regionCode;

    /**
     * 板块、商圈编码
     */
    @JsonProperty("plate")
    @TableField("plate")
    private String plate;

    /**
     * 板块、商圈编码
     */
    @JsonProperty("plate_code")
    @TableField("plate_code")
    private String plateCode;

    /**
     * 源小区名称
     */
    @JsonProperty("title")
    @TableField("title")
    private String title;

    /**
     * 源小区ID
     */
    @JsonProperty("title_id")
    @TableField("title_id")
    private String titleId;

    /**
     * 小区别名
     */
    @JsonProperty("alias")
    @TableField("alias")
    private String alias;

    /**
     * 访问该小区的url
     */
    @TableField("current_base_url")
    private String currentBaseUrl;

    /**
     * 经度
     */
    @TableField("lng")
    private String lng;

    /**
     * 纬度
     */
    @TableField("lat")
    private String lat;

    private String address;

    /**
     * 小区均价
     */
    @TableField("average_price")
    private String averagePrice;

    /**
     * 产权年限
     */
    @TableField("property_year")
    private String propertyYear;

    /**
     * 交易权属
     */
    @TableField("trading_rights")
    private String tradingRights;

    /**
     * 在售数量,num_for_sale
     */
    @TableField("sale")
    private String sale;

    /**
     * 在租数量,rent_num
     */
    @TableField("rent")
    private String rent;

    /**
     * 小区入口
     */
    @TableField("gate")
    private String gate;

    /**
     * 所处环线位置
     */
    @TableField("loop_position")
    private String loopPosition;

    /**
     * 楼栋数量
     */
    @TableField("loudong_num")
    private String loudongNum;

    /**
     * 总户数
     */
    @TableField("home_total")
    private String homeTotal;

    /**
     * 竣工日期
     */
    @TableField("build_year")
    private String buildYear;

    /**
     * 建筑类型
     */
    @TableField("build_type")
    private String buildType;

    /**
     * 建筑结构
     */
    @TableField("build_str")
    private String buildStr;

    /**
     * 物业费
     */
    @TableField("property_price")
    private String propertyPrice;

    /**
     * 物业公司
     */
    @TableField("property_company")
    private String propertyCompany;

    /**
     * 物业电话
     */
    @TableField("property_phone")
    private String propertyPhone;

    /**
     * 物业类型
     */
    @TableField("property_type")
    private String propertyType;

    /**
     * 物业办公地点
     */
    @TableField("pro_service_addr")
    private String proServiceAddr;

    /**
     * 开盘时间
     */
    @TableField("pro_on_time")
    private String proOnTime;

    /**
     * 停车位
     */
    @TableField("park_num")
    private String parkNum;

    /**
     * 地上车位数
     */
    @TableField("upper_num")
    private String upperNum;

    /**
     * 地下车位数
     */
    @TableField("under_num")
    private String underNum;

    /**
     * 车位配比
     */
    @TableField("park_ratio")
    private String parkRatio;

    /**
     * 停车费
     */
    @TableField("fixed_charge")
    private String fixedCharge;

    /**
     * 在售停车位
     */
    @TableField("parking_sale_ind")
    private String parkingSaleInd;

    /**
     * 绿化率
     */
    @TableField("green_rate")
    private String greenRate;

    /**
     * 容积率
     */
    @TableField("plot_rate")
    private String plotRate;

    /**
     * 用水类型
     */
    @TableField("water")
    private String water;

    /**
     * 用电类型
     */
    @TableField("supply_electricity")
    private String supplyElectricity;

    /**
     * 供暖类型
     */
    @TableField("heating")
    private String heating;

    /**
     * 供暖费用
     */
    @TableField("heating_cost")
    private String heatingCost;

    /**
     * 燃气费用
     */
    @TableField("gas")
    private String gas;

    /**
     * 房屋用途
     */
    @TableField("uses")
    private String uses;

    /**
     * 总面积
     */
    @TableField("build_area")
    private String buildArea;

    /**
     * 户型面积
     */
    @TableField("act_area")
    private String actArea;

    @TableField("air_condition_ind")
    private String airConditionInd;

    /**
     * 外墙风格
     */
    @TableField("ext_wall_style")
    private String extWallStyle;

    /**
     * 立面风格
     */
    @TableField("facade_style")
    private String facadeStyle;

    /**
     * 项目简介
     */
    @TableField("introduction")
    private String introduction;

    @TableField("arch_des")
    private String archDes;

    /**
     * 楼栋描述
     */
    @TableField("building_des")
    private String buildingDes;

    @TableField("loudong_url")
    private String loudongUrl;

    @TableField("layout_url")
    private String layoutUrl;

    @TableField("detail_url")
    private String detailUrl;

    /**
     * 地铁
     */
    @TableField("railway_adress")
    private String railwayAdress;

    @TableField("community_close_ind")
    private String communityCloseInd;

    /**
     * 人车飞流
     */
    @TableField("person_div_car_ind")
    private String personDivCarInd;

    /**
     * 机动车非机动车分流
     */
    @TableField("nonmotor_garage_ind")
    private String nonmotorGarageInd;

    /**
     * 保安亭数
     */
    @TableField("security_booth_num")
    private String securityBoothNum;

    /**
     * 保安人数
     */
    @TableField("security_person_num")
    private String securityPersonNum;

    /**
     * 巡逻人数
     */
    @TableField("security_patrol_num")
    private String securityPatrolNum;

    @TableField("intel_gate_ind")
    private String intelGateInd;

    @TableField("security_allday_ind")
    private String securityAlldayInd;

    @TableField("gate_control_ind")
    private String gateControlInd;

    @TableField("monitor_ind")
    private String monitorInd;

    @TableField("police_networking_ind")
    private String policeNetworkingInd;

    @TableField("brand_corp")
    private String brandCorp;

    @TableField("series")
    private String series;

    @TableField("fist_page_url")
    private String fistPageUrl;

    @TableField("region_url")
    private String regionUrl;

    /**
     * 安全管理
     */
    @TableField("security_desc")
    private String securityDesc;

    /**
     * 通讯设备
     */
    @TableField("communication_dev")
    private String communicationDev;

    /**
     * 邮政编码
     */
    @TableField("postcodes")
    private String postcodes;

    /**
     * 卫生服务
     */
    @TableField("health_desc")
    private String healthDesc;

    /**
     * 其它
     */
    @TableField("other")
    private String other;

    /**
     * 电梯数量
     */
    @TableField("elevator_desc")
    private String elevatorDesc;

    @TableField("nid")
    private String nid;

    @TableField("loop_price")
    private String loopPrice;

    /**
     * 对口学校
     */
    @TableField("duikou_school")
    private String duikouSchool;

    @TableField("create_time")
    private Date createTime;

}
