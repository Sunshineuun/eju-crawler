package com.qiusm.eju.crawler.service.base;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.base.City;

public interface ICityService
        extends IService<City> {
    City selectByBkCityCode(String cityCode);
}
