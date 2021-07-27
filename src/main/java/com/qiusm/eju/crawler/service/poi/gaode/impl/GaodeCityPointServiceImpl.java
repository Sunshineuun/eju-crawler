package com.qiusm.eju.crawler.service.poi.gaode.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityPoint;
import com.qiusm.eju.crawler.mapper.poi.gaode.GaodeCityPointMapper;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeCityPointService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class GaodeCityPointServiceImpl
        extends ServiceImpl<GaodeCityPointMapper, GaodeCityPoint>
        implements IGaodeCityPointService {
}
