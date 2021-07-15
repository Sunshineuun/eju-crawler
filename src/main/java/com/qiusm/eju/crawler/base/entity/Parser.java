package com.qiusm.eju.crawler.base.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiushengming
 */
@Slf4j
@Data
@TableName("tb_crawler_parser")
@EqualsAndHashCode(callSuper = true)
public class Parser extends SuperEntity<Parser> {

    private static final long serialVersionUID = 1L;

    /**
     * Parser名称
     */
    private String name;
    /**
     * Parser类
     */
    private String clazz;
    /**
     * Parser代码(扩展)
     */
    private String code;
    /**
     * 任务类型,(1，2，3，4，5，6 链家，贝壳等等)
     */
    private String type;
    /**
     * 解析器产生数据存放的目录
     */
    @TableField("data_table_name")
    private String dataTableName;
    /**
     * 上级下载器id
     */
    @TableField("parent_id")
    private Long parentId;

    @TableField(exist = false)
    List<Parser> childList = new ArrayList<>();

}
