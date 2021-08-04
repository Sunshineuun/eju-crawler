package com.qiusm.eju.crawler.service.base.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.base.City;
import com.qiusm.eju.crawler.mapper.base.CityMapper;
import com.qiusm.eju.crawler.service.base.ICityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl
        extends ServiceImpl<CityMapper, City>
        implements ICityService {
}
