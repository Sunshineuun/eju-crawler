package com.qiusm.eju.crawler.government.nj.dao;

import com.qiusm.eju.crawler.government.nj.entity.FdNanJinHouse;
import com.qiusm.eju.crawler.government.nj.entity.FdNanJinHouseExample;
import com.qiusm.eju.crawler.government.nj.entity.FdNanJinHouseWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FdNanJinHouseMapper {
    long countByExample(FdNanJinHouseExample example);

    int deleteByExample(FdNanJinHouseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdNanJinHouseWithBLOBs record);

    int insertSelective(FdNanJinHouseWithBLOBs record);

    List<FdNanJinHouseWithBLOBs> selectByExampleWithBLOBs(FdNanJinHouseExample example);

    List<FdNanJinHouse> selectByExample(FdNanJinHouseExample example);

    FdNanJinHouseWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdNanJinHouseWithBLOBs record, @Param("example") FdNanJinHouseExample example);

    int updateByExampleWithBLOBs(@Param("record") FdNanJinHouseWithBLOBs record, @Param("example") FdNanJinHouseExample example);

    int updateByExample(@Param("record") FdNanJinHouse record, @Param("example") FdNanJinHouseExample example);

    int updateByPrimaryKeySelective(FdNanJinHouseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FdNanJinHouseWithBLOBs record);

    int updateByPrimaryKey(FdNanJinHouse record);
}