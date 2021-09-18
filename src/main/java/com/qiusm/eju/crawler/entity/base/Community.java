package com.qiusm.eju.crawler.entity.base;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 小区信息表
 */
@Data
@TableName("community")
@EqualsAndHashCode(callSuper = true)
public class Community extends SuperEntity<Community> {
    private static final long serialVersionUID = 4830632905143155494L;

    /**
     * 来源
     */
    private String source;
    /**
     * 小区名称
     */
    private String community;
    /**
     * 小区ID
     */
    private String communityId;
    /**
     * ajk另一个小区id，用来查询骨架数据的
     */
    private String ajkCommunityId;
    /**
     * 小区均价
     */
    private String avgPrice;
    /**
     * 均价月份
     */
    private String avgPriceMonth;
    /**
     * 月涨幅
     */
    private String avgPriceMonthChange;
    /**
     * 小区地址
     */
    private String communityAdd;
    /**
     * 城市
     */
    private String city;
    /**
     * 城市ID
     */
    private String cityId;
    /**
     * 区域
     */
    private String region;
    /**
     * 区域ID
     */
    private String regionId;
    /**
     * 板块
     */
    private String plate;
    /**
     * 板块ID
     */
    private String plateId;
    /**
     * 高德坐标
     */
    private String gd;
    /**
     * 百度坐标
     */
    private String bd;
    /**
     * 建筑年代
     */
    private String buildYear;
    /**
     * 建筑类型
     */
    private String buildType;
    /**
     * 开发企业
     */
    private String devEnt;
    /**
     * 开盘时间
     */
    private String openTime;
    /**
     * 供暖类型
     */
    private String heatType;
    /**
     * 用水类型
     */
    private String waterUseType;
    /**
     * 用电类型
     */
    private String electricityType;
    /**
     * 燃气费用
     */
    private String gasCost;
    /**
     * 停车位
     */
    private String parkSpace;
    /**
     * 停车费
     */
    private String parkFee;
    /**
     * 容积率
     */
    private String volumeRate;
    /**
     * 绿化率
     */
    private String greeningRate;
    /**
     * 物业公司
     */
    private String propertyCompany;
    /**
     * 物业费
     */
    private String propertyExpenses;
    /**
     * 物业电话
     */
    private String propertyPhone;
    /**
     * 物业类型
     */
    private String propertyType;
    /**
     * 在售描述
     */
    private String sale;
    /**
     * 在租描述
     */
    private String rent;
    /**
     * 交易权属
     */
    private String tradeOwnership;
    /**
     * 实景图
     */
    private String realPicture;
    /**
     * 周边配套
     */
    private String support;
    /**
     * 总户数
     */
    private String totalHouse;
    /**
     * 总面积
     */
    private String totalArea;
    /**
     * 创建
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
