package com.qiusm.eju.crawler.entity.poi.gaode;

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
@TableName("gaode_poi")
@EqualsAndHashCode(callSuper = true)
public class GaodePoi
        extends SuperEntity<GaodePoi> {

    private String uid;

    private String name;

    private String parent;

    private String tag;

    @TableField("child_type")
    private String childType;

    private String type;

    @TableField("type_code")
    private String typeCode;

    @TableField("biz_type")
    private String bizType;

    private String address;

    private String location;

    private String tel;

    @TableField("post_code")
    private String postCode;

    private String website;

    private String email;

    private String pname;

    private String pcode;

    @TableField("city_name")
    private String cityName;

    @TableField("city_code")
    private String cityCode;

    private String adname;

    private String adcode;

    private String importance;

    @TableField("shop_id")
    private String shopId;

    @TableField("shop_info")
    private String shopInfo;

    @TableField("poi_weight")
    private String poiWeight;

    @TableField("grid_code")
    private String gridCode;

    private String distance;

    @TableField("business_area")
    private String businessArea;

    @TableField("navi_poiid")
    private String naviPoiid;

    @TableField("entr_location")
    private String entrLocation;

    private String alias;

    private String photos;

    @TableField("tag_code")
    private String tagCode;

    @TableField("tag_name")
    private String tagName;

    private String version;

    @TableField("create_time")
    private Date createTime;

}