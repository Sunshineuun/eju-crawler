package com.qiusm.eju.crawler.service.poi.gaode.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodePoi;
import com.qiusm.eju.crawler.mapper.poi.gaode.GaodePoiMapper;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodePoiService;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Service
public class GaodePoiServiceImpl
        extends ServiceImpl<GaodePoiMapper, GaodePoi>
        implements IGaodePoiService {
}
