package com.qiusm.eju.crawler.service.base.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.base.City;
import com.qiusm.eju.crawler.mapper.base.CityMapper;
import com.qiusm.eju.crawler.service.base.ICityService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CityServiceImpl
        extends ServiceImpl<CityMapper, City>
        implements ICityService {

    @Override
    public City selectByBkCityCode(String cityCode) {
        EntityWrapper<City> wrapper = new EntityWrapper<>();
        wrapper.eq("bk_code", cityCode);
        return this.selectOne(wrapper);
    }

    @Override
    public City selectCityByEqMap(Map<String, String> condition) {
        EntityWrapper<City> wrapper = new EntityWrapper<>();
        condition.forEach(wrapper::eq);
        return this.selectOne(wrapper);
    }
}
