package com.qiusm.eju.crawler.service.poi.gaode.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityFence;
import com.qiusm.eju.crawler.mapper.poi.gaode.GaodeCityFenceMapper;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeCityFenceService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class GaodeCityFenceServiceImpl
        extends ServiceImpl<GaodeCityFenceMapper, GaodeCityFence>
        implements IGaodeCityFenceService {
}
