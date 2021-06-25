package com.qiusm.eju.crawler.poi.gaode.dao;

import com.qiusm.eju.crawler.poi.gaode.entity.GaodePoi;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodePoiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GaodePoiMapper {
    long countByExample(GaodePoiExample example);

    int deleteByExample(GaodePoiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GaodePoi record);

    int insertSelective(GaodePoi record);

    List<GaodePoi> selectByExample(GaodePoiExample example);

    GaodePoi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GaodePoi record, @Param("example") GaodePoiExample example);

    int updateByExample(@Param("record") GaodePoi record, @Param("example") GaodePoiExample example);

    int updateByPrimaryKeySelective(GaodePoi record);

    int updateByPrimaryKey(GaodePoi record);
}