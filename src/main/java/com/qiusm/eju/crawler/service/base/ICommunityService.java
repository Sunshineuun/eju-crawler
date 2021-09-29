package com.qiusm.eju.crawler.service.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.base.Community;

import java.util.List;

/**
 * 小区信息服务层接口
 *
 * @author qiushengming
 */
public interface ICommunityService extends IService<Community> {
    /**
     * 检查重复并插入
     *
     * @param community 小区实体
     */
    void checkInsert(Community community);

    List<Community> getCommunityByCity(String city, String source);

    Page<Community> getCommunityAjkNotDetail(int page);
}
