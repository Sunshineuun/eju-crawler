package com.qiusm.eju.crawler.entity.base;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 城市列表
 */
@Data
@TableName("eju_city_list")
@EqualsAndHashCode(callSuper = true)
public class City
        extends SuperEntity<City> {

    private String city;

    private String code;

    private String bkCode;

    private String bkPinyinCode;

    private String ajkCode;

    private String ajkPinyinCode;

    private String provinceCd;

    private String provinceName;
}
