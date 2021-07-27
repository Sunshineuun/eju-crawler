package com.qiusm.eju.crawler.service.poi.gaode;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityFence;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityPoint;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodePoi;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qiushengming
 */
public interface IGaodeService {
    List<GaodeCityPoiInfo> selectAllCityInfo();

    List<GaodeCityPoiInfo> selectCityInfoExample(EntityWrapper<GaodeCityPoiInfo> entityWrapper);

    List<GaodeCityFence> selectCityFenceByExample(EntityWrapper<GaodeCityFence> entityWrapper);

    List<GaodeCityPoint> selectCityPointByExample(EntityWrapper<GaodeCityPoint> entityWrapper);

    @Transactional(rollbackFor = Exception.class)
    void saveCityFence(GaodeCityPoiInfo cityPoiInfo, String fence);

    @Transactional(rollbackFor = Exception.class)
    void saveCityPoint(List<GaodeCityPoint> cityPoints);

    @Transactional(rollbackFor = Exception.class)
    void updateCityPointByKey(GaodeCityPoint point);

    @Transactional(rollbackFor = Exception.class)
    void savePoi(List<GaodePoi> pois);
}
