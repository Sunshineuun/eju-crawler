package com.qiusm.eju.crawler.service.base;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.base.CommunityDetail;

/**
 * @author qiushengming
 */
public interface ICommunityDetailService extends IService<CommunityDetail> {
    boolean insertIfAbsent(CommunityDetail detail);
}
