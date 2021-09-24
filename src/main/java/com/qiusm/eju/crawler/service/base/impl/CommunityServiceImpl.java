package com.qiusm.eju.crawler.service.base.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.base.Community;
import com.qiusm.eju.crawler.mapper.base.CommunityMapper;
import com.qiusm.eju.crawler.service.base.ICommunityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小区信息服务实现层
 *
 * @author qiushengming
 */
@Service
public class CommunityServiceImpl
        extends ServiceImpl<CommunityMapper, Community>
        implements ICommunityService {
    @Override
    public void checkInsert(Community community) {
        EntityWrapper<Community> wrapper = new EntityWrapper<>();
        wrapper.eq("community", community.getCommunity())
                .eq("community_id", community.getCommunityId())
                .eq("source", community.getSource());
        int count = this.selectCount(wrapper);
        if (count == 0) {
            this.insert(community);
        }
    }

    @Override
    public List<Community> getCommunityByCity(String city, String source) {
        EntityWrapper<Community> wrapper = new EntityWrapper<>();
        wrapper.eq("source", source)
                .eq("city", city);
        return this.selectList(wrapper);
    }

    @Override
    public Page<Community> getCommunityAjkNotDetail(int currPage) {
        EntityWrapper<Community> wrapper = new EntityWrapper<>();
        wrapper.eq("source", "AJK")
                .isNull("property_company");
        Page<Community> page = new Page<>(currPage, 1000, "id");
        return this.selectPage(page, wrapper);
    }
}
