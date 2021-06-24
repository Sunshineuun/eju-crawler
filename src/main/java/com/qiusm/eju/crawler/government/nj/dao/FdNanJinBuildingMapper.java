package com.qiusm.eju.crawler.government.nj.dao;

import com.qiusm.eju.crawler.government.nj.entity.FdNanJinBuilding;
import com.qiusm.eju.crawler.government.nj.entity.FdNanJinBuildingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FdNanJinBuildingMapper {
    long countByExample(FdNanJinBuildingExample example);

    int deleteByExample(FdNanJinBuildingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdNanJinBuilding record);

    int insertSelective(FdNanJinBuilding record);

    List<FdNanJinBuilding> selectByExample(FdNanJinBuildingExample example);

    FdNanJinBuilding selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdNanJinBuilding record, @Param("example") FdNanJinBuildingExample example);

    int updateByExample(@Param("record") FdNanJinBuilding record, @Param("example") FdNanJinBuildingExample example);

    int updateByPrimaryKeySelective(FdNanJinBuilding record);

    int updateByPrimaryKey(FdNanJinBuilding record);
}