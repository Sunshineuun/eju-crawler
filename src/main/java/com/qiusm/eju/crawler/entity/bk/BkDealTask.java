package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import com.qiusm.eju.crawler.enums.CommunitySkeletonTaskStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("bk_deal_task")
@EqualsAndHashCode(callSuper = true)
public class BkDealTask extends SuperEntity<BkDealTask> {
    private String city;
    private String cityPy;
    private String cityId;
    /**
     * 初始化:0、运行:1、抓取异常:2、抓取完毕:3、推送成功清洗中:4、 <br>
     * 任务进来的初始状态是创建，
     */
    private String state;
    private String stateDesc;
    @TableField("task_desc")
    private String desc;
    private Date createTime;
    private Date endTime;

    @TableField(exist = false)
    private CommunitySkeletonTaskStateEnum stateEnum;

    public void setStateEnum(CommunitySkeletonTaskStateEnum stateEnum) {
        this.stateEnum = stateEnum;
        this.state = stateEnum.getCode();
        this.stateDesc = stateEnum.getMsg();
        this.endTime = new Date();
    }
}
