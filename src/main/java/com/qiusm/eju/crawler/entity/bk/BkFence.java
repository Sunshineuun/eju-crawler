package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qiushengming
 */
@Data
@TableName("bk_fence")
@EqualsAndHashCode(callSuper = true)
public class BkFence extends SuperEntity<BkFence> {

    @TableField("city_code")
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    private String district;

    private String bizcircle;

    private String longitude;

    private String latitude;

    private String type;

    private String version;

    private String fence;
}