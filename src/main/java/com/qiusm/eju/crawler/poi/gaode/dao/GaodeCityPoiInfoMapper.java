package com.qiusm.eju.crawler.poi.gaode.dao;

import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfo;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoiInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GaodeCityPoiInfoMapper {
    long countByExample(GaodeCityPoiInfoExample example);

    int deleteByExample(GaodeCityPoiInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GaodeCityPoiInfo record);

    int insertSelective(GaodeCityPoiInfo record);

    List<GaodeCityPoiInfo> selectByExample(GaodeCityPoiInfoExample example);

    GaodeCityPoiInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GaodeCityPoiInfo record, @Param("example") GaodeCityPoiInfoExample example);

    int updateByExample(@Param("record") GaodeCityPoiInfo record, @Param("example") GaodeCityPoiInfoExample example);

    int updateByPrimaryKeySelective(GaodeCityPoiInfo record);

    int updateByPrimaryKey(GaodeCityPoiInfo record);
}