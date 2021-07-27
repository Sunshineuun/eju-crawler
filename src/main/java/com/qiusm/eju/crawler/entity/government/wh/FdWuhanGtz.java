package com.qiusm.eju.crawler.entity.government.wh;

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
@TableName("fd_wuhan_gtz")
@EqualsAndHashCode(callSuper = true)
public class FdWuhanGtz
        extends SuperEntity<FdWuhanGtz> {

    @TableField("house_id")
    private Long houseId;
    @TableField("city_name")
    private String cityName;
    @TableField("project_id")
    private String projectId;
    @TableField("gtz_no")
    private String gtzNo;
    @TableField("url")
    private String url;

    @TableField("status")
    private String status;
    @TableField("version")
    private String version;
    @TableField("create_time")
    private Date createTime = new Date();
}