package com.qiusm.eju.crawler.poi.gaode.dao;

import com.qiusm.eju.crawler.poi.constant.CityLevel;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFence;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFenceExample;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfoExample;
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

    @Transactional(rollbackFor = Exception.class)
    public void saveCityInfo(List<GaodeCityPoiInfo> infos) {
        infos.forEach(var -> cityPoiInfoMapper.insert(var));
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
}
