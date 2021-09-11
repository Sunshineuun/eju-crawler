package com.qiusm.eju.crawler.entity.ajk;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ajk区域列表
 *
 * @author qiushengming
 */
@Data
@TableName(value = "anjuke_area_info")
@EqualsAndHashCode(callSuper = true)
public class AjkArea extends SuperEntity<AjkArea> {
    @TableField("city_name")
    private String city;
    private String cityId;
    private String areaName;
    private String areaId;
    @TableField("pinyin")
    private String areaPinyin;
}
