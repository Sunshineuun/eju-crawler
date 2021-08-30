package com.qiusm.eju.crawler.entity.ajk;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@TableName("ajk_community_info")
public class AjkCommunityInfo extends SuperEntity<AjkCommunityInfo> {
    @JsonProperty("area_name")
    private String areaName;
    @JsonProperty("detail_url")
    private String detailUrl;
    @JsonProperty("plate")
    private String plate;
    @JsonProperty("area_id")
    private String areaId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("build_year")
    private String buildYear;
    @JsonProperty("property_company")
    private String propertyCompany;
    @JsonProperty("build_type")
    private String buildType;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("property_price")
    private String propertyPrice;
    @JsonProperty("address")
    private String address;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("title_id")
    private String titleId;
    @JsonProperty("average_price")
    private String averagePrice;
    @JsonProperty("average_price_month")
    private String averagePriceMonth;
    @JsonProperty("page")
    @TableField("page")
    private String page;
    @JsonProperty("trading_rights")
    private String tradingRights;
    @JsonProperty("region")
    private String region;
    @JsonProperty("city_id")
    private String cityId;
    @JsonProperty("total_house_hold_num")
    private String totalHouseHoldNum;
}
