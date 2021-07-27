package com.qiusm.eju.crawler.service.poi.gaode.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.constant.poi.CityLevel;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityFence;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodeCityPoint;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodePoi;
import com.qiusm.eju.crawler.mapper.poi.gaode.GaodePoiMapper;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeCityFenceService;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeCityPoiInfoService;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeCityPointService;
import com.qiusm.eju.crawler.service.poi.gaode.IGaodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class GaodeServiceImpl implements IGaodeService {

    @Resource
    private IGaodeCityPoiInfoService cityPoiInfoService;

    @Resource
    private IGaodeCityFenceService cityFenceService;

    @Resource
    private IGaodeCityPointService cityPointService;

    @Resource
    private GaodePoiMapper poiMapper;

    @Override
    public List<GaodeCityPoiInfo> selectAllCityInfo() {
        EntityWrapper<GaodeCityPoiInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.isNull("fence_id").ne("level", CityLevel.COUNTRY.getCode());
        return cityPoiInfoService.selectList(entityWrapper);
    }

    @Override
    public List<GaodeCityPoiInfo> selectCityInfoExample(EntityWrapper<GaodeCityPoiInfo> entityWrapper) {
        return cityPoiInfoService.selectList(entityWrapper);
    }

    @Override
    public List<GaodeCityFence> selectCityFenceByExample(EntityWrapper<GaodeCityFence> entityWrapper) {
        return cityFenceService.selectList(entityWrapper);
    }

    @Override
    public List<GaodeCityPoint> selectCityPointByExample(EntityWrapper<GaodeCityPoint> entityWrapper) {
        return cityPointService.selectList(entityWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCityFence(GaodeCityPoiInfo cityPoiInfo, String fence) {
        GaodeCityFence cityFence = new GaodeCityFence();
        cityFence.setFence(fence);
        cityFence.setCreateTime(new Date());
        cityFence.setName(cityPoiInfo.getName());
        cityFence.insert();
        cityPoiInfo.setFenceId(cityFence.getId());
        cityPoiInfo.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCityPoint(List<GaodeCityPoint> cityPoints) {
        Date now = new Date();
        cityPoints.forEach(var -> {
            var.setCreateTime(now);
            var.insert();
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCityPointByKey(GaodeCityPoint point) {
        point.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePoi(List<GaodePoi> pois) {
        Date now = new Date();
        pois.forEach(var -> {
            var.setCreateTime(now);
            var.insert();
        });
    }
}
