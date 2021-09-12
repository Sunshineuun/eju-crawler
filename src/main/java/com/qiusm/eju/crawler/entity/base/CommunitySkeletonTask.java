package com.qiusm.eju.crawler.entity.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.enums.CommunitySkeletonTaskStateEnum;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiushengming
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("community_skeleton_task")
public class CommunitySkeletonTask
        extends SuperEntity<CommunitySkeletonTask>
        implements Serializable {

    private String communityId;
    private String communityName;
    private String source = "BK";
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

    public CommunitySkeletonTask() {

    }

    public void setStateEnum(CommunitySkeletonTaskStateEnum stateEnum) {
        this.stateEnum = stateEnum;
        this.state = stateEnum.getCode();
        this.stateDesc = stateEnum.getMsg();
    }

    /**
     * 任务是否结束
     *
     * @return 结束：true；没结束：false。
     */
    public Boolean isOver() {
        return StringUtils.equals("2", this.state)
                || StringUtils.equals("3", this.state);
    }

    /**
     * 是否抓取完毕
     *
     * @return state == 3：true；state != 3：false；
     */
    public Boolean isComplete() {
        return StringUtils.equals("3", this.state);
    }

    /**
     * 是否抓取异常
     *
     * @return state == 2：true；state != 2：false；
     */
    public Boolean isException() {
        return StringUtils.equals("2", this.state);
    }

    /**
     * 是否初始化
     *
     */
    public Boolean isInit() {
        return StringUtils.equals("0", this.state);
    }

    public Boolean isRunning() {
        return StringUtils.equals("1", this.state);
    }
}