package com.qiusm.eju.crawler.entity.poi;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("location_key")
public class LocationKey
        extends SuperEntity<LocationKey> {
    @TableField("apply_key")
    private String applyKey;
    private String source;
    private String count;
}
