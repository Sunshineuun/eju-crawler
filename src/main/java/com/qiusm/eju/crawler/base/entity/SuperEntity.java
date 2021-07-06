package com.qiusm.eju.crawler.base.entity;


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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lz
 * @version 2018-10-26
 * 实体父类
 */
@Data
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    @JSONField(serialize = false)
    protected Integer version;

    @TableField("create_time")
    protected LocalDateTime createTime;

    @TableField("update_time")
    protected LocalDateTime updateTime;


    /**
     * 删除状态  1 删除  0未删除
     */
    @JSONField(serialize = false)
    @TableLogic
    protected Integer deleted;

    @JSONField(serialize = false)
    @TableField(exist = false)
    protected Integer[] statusArray;

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

    /**
     * 插入更新时间和创建时间
     */
    public void preHandle() {
        LocalDateTime date = LocalDateTime.now();
        if (this.id == null) {
            createTime = date;
        }
        updateTime = date;
    }

    /**
     * 是否已删除
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isDelete() {
        return deleted != null && deleted == 1;
    }


    /**
     * 是否是新数据
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isNewRecord() {
        return this.id == null;
    }
}