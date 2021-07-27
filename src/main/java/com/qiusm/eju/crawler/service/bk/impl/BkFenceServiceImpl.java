package com.qiusm.eju.crawler.service.bk.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.bk.BkFence;
import com.qiusm.eju.crawler.mapper.bk.BkFenceMapper;
import com.qiusm.eju.crawler.service.bk.IBkFenceService;
import org.springframework.stereotype.Service;

/**
 * 围栏
 *
 * @author qiushengming
 */
@Service
public class BkFenceServiceImpl
        extends ServiceImpl<BkFenceMapper, BkFence>
        implements IBkFenceService {
}
