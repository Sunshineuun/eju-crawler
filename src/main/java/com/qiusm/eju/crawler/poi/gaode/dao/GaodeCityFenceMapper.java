package com.qiusm.eju.crawler.poi.gaode.dao;

import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFence;
import com.qiusm.eju.crawler.poi.gaode.entity.GaodeCityFenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GaodeCityFenceMapper {
    long countByExample(GaodeCityFenceExample example);

    int deleteByExample(GaodeCityFenceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GaodeCityFence record);

    int insertSelective(GaodeCityFence record);

    List<GaodeCityFence> selectByExampleWithBLOBs(GaodeCityFenceExample example);

    List<GaodeCityFence> selectByExample(GaodeCityFenceExample example);

    GaodeCityFence selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GaodeCityFence record, @Param("example") GaodeCityFenceExample example);

    int updateByExampleWithBLOBs(@Param("record") GaodeCityFence record, @Param("example") GaodeCityFenceExample example);

    int updateByExample(@Param("record") GaodeCityFence record, @Param("example") GaodeCityFenceExample example);

    int updateByPrimaryKeySelective(GaodeCityFence record);

    int updateByPrimaryKeyWithBLOBs(GaodeCityFence record);

    int updateByPrimaryKey(GaodeCityFence record);
}