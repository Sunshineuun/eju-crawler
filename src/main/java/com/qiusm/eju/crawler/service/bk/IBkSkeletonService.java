package com.qiusm.eju.crawler.service.bk;

import com.alibaba.fastjson.JSONArray;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;

/**
 * 贝壳骨架抓取服务
 *
 * @author qiushengming
 */
public interface IBkSkeletonService {

    /**
     * 小区骨架数据抓取
     *
     * @param task 小区骨架任务信息
     * @return 骨架数据列表
     */
    JSONArray communitySkeleton(CommunitySkeletonTask task);
}
