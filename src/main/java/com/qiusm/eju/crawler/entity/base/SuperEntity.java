package com.qiusm.eju.crawler.entity.base;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author qiushengming
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuperEntity<T extends Model<?>>
        extends Model<T>
        implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    protected Pagination page;

    @Override
    public String toString() {
        return JSONUtils.toJSONString(this);
    }

}