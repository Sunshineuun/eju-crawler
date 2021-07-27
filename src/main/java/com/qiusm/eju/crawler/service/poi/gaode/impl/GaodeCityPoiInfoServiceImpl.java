package com.qiusm.eju.crawler.service.poi.gaode.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.mapper.poi.gaode.GaodeCityPoiInfoMapper;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeCityPoiInfoService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class GaodeCityPoiInfoServiceImpl
        extends ServiceImpl<GaodeCityPoiInfoMapper, GaodeCityPoiInfo>
        implements IGaodeCityPoiInfoService {
}
