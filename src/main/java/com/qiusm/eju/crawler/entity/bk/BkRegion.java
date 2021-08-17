package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("bk_region")
@EqualsAndHashCode(callSuper = true)
public class BkRegion extends SuperEntity<BkRegion> {
    private String cityName;
    private String cityId;
    private String plate;
    private String plateCd;
    private String region;
    private String regionCd;
}
