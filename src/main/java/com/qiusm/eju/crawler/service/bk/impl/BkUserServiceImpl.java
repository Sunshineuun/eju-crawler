package com.qiusm.eju.crawler.service.bk.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.mapper.bk.BkUserMapper;
import com.qiusm.eju.crawler.service.bk.IBkUserService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class BkUserServiceImpl
        extends ServiceImpl<BkUserMapper, BkUser>
        implements IBkUserService {
}
