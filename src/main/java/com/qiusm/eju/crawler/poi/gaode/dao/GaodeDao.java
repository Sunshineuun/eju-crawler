package com.qiusm.eju.crawler.poi.gaode.dao;

import com.qiusm.eju.crawler.poi.constant.CityLevel;
import com.qiusm.eju.crawler.poi.gaode.entity.*;
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
public class GaodeDao {

    @Resource
    private GaodeCityPoiInfoMapper cityPoiInfoMapper;

    @Resource
    private GaodeCityFenceMapper cityFenceMapper;

    @Resource
    private GaodeCityPointMapper cityPointMapper;

    @Resource
    private GaodePoiMapper poiMapper;

    public List<GaodeCityPoiInfo> selectAllCityInfo() {
        GaodeCityPoiInfoExample example = new GaodeCityPoiInfoExample();
        example.createCriteria().andFenceIdIsNull()
                .andLevelNotEqualTo(CityLevel.COUNTRY.getCode());

        return cityPoiInfoMapper.selectByExample(example);
    }

    public List<GaodeCityPoiInfo> selectCityInfoExample(GaodeCityPoiInfoExample example) {
        return cityPoiInfoMapper.selectByExample(example);
    }

    public List<GaodeCityFence> selectCityFenceByExample(GaodeCityFenceExample example) {
        return cityFenceMapper.selectByExampleWithBLOBs(example);
    }

    public List<GaodeCityPoint> selectCityPointByExample(GaodeCityPointExample example) {
        return cityPointMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCityInfo(List<GaodeCityPoiInfo> infos) {
        Date now = new Date();
        infos.forEach(var -> {
            var.setCreateTime(now);
            cityPoiInfoMapper.insert(var);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCityFence(GaodeCityPoiInfo cityPoiInfo, String fence) {
        GaodeCityFence cityFence = new GaodeCityFence();
        cityFence.setFence(fence);
        cityFence.setCreateTime(new Date());
        cityFence.setName(cityPoiInfo.getName());
        cityFenceMapper.insert(cityFence);
        cityPoiInfo.setFenceId(cityFence.getId());

        cityPoiInfoMapper.updateByPrimaryKeySelective(cityPoiInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCityPoint(List<GaodeCityPoint> cityPoints) {
        Date now = new Date();
        cityPoints.forEach(var -> {
            var.setCreateTime(now);
            cityPointMapper.insert(var);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCityPointByKey(GaodeCityPoint point) {
        cityPointMapper.updateByPrimaryKey(point);
    }

    @Transactional(rollbackFor = Exception.class)
    public void savePoi(List<GaodePoi> pois) {
        Date now = new Date();
        pois.forEach(var -> {
            var.setCreateTime(now);
            poiMapper.insert(var);
        });
    }
}
