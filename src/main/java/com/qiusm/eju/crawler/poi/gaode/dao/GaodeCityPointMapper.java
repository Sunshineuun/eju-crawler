package com.qiusm.eju.crawler.poi.gaode.dao;

import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPoint;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityPointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GaodeCityPointMapper {
    long countByExample(GaodeCityPointExample example);

    int deleteByExample(GaodeCityPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GaodeCityPoint record);

    int insertSelective(GaodeCityPoint record);

    List<GaodeCityPoint> selectByExample(GaodeCityPointExample example);

    GaodeCityPoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GaodeCityPoint record, @Param("example") GaodeCityPointExample example);

    int updateByExample(@Param("record") GaodeCityPoint record, @Param("example") GaodeCityPointExample example);

    int updateByPrimaryKeySelective(GaodeCityPoint record);

    int updateByPrimaryKey(GaodeCityPoint record);
}