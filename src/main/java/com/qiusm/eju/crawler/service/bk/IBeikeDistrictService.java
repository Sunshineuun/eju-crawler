package com.qiusm.eju.crawler.service.bk;

import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfo;

import java.util.List;

/**
 * @author qiushengming
 */
public interface IBeikeDistrictService {
    void city(String cityCode);

    void district(String cityCode, String... arg);

    void districtPrivate(GaodeCityPoiInfo cityPoiInfo, String... arg);

    void jsonParser(String cityCode);

    List<String> loadCityList();
}
