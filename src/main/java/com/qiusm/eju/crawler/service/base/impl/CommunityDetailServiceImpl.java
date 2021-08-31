package com.qiusm.eju.crawler.service.base.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.base.CommunityDetail;
import com.qiusm.eju.crawler.mapper.base.CommunityDetailMapper;
import com.qiusm.eju.crawler.service.base.ICommunityDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class CommunityDetailServiceImpl
        extends ServiceImpl<CommunityDetailMapper, CommunityDetail>
        implements ICommunityDetailService {

    @Override
    public boolean insertIfAbsent(CommunityDetail detail) {
        EntityWrapper<CommunityDetail> wrapper = new EntityWrapper<>();
        wrapper.eq("title_id", detail.getTitleId());
        CommunityDetail ifDetail = this.selectOne(wrapper);
        if (ifDetail == null) {
            return this.insert(detail);
        }
        return true;
    }
}
