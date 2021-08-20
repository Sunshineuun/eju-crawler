package com.qiusm.eju.crawler.service.base;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;

import java.util.List;

public interface ICommunitySkeletonTaskService extends IService<CommunitySkeletonTask> {
    /**
     * 按照小区id+城市进行判重复
     *
     * @param task 任务
     */
    void checkAndAdd(CommunitySkeletonTask task);

    /**
     * 多个状态用逗号分割
     *
     * @param state {@link com.qiusm.eju.crawler.enums.CommunitySkeletonTaskStateEnum}
     * @return 任务列表
     */
    List<CommunitySkeletonTask> getByInState(String state);
}
