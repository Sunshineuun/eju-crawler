package com.qiusm.eju.crawler.government.nj.dao;

import com.qiusm.eju.crawler.government.nj.entity.FdNanJinUnit;
import com.qiusm.eju.crawler.government.nj.entity.FdNanJinUnitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FdNanJinUnitMapper {
    long countByExample(FdNanJinUnitExample example);

    int deleteByExample(FdNanJinUnitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdNanJinUnit record);

    int insertSelective(FdNanJinUnit record);

    List<FdNanJinUnit> selectByExample(FdNanJinUnitExample example);

    FdNanJinUnit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdNanJinUnit record, @Param("example") FdNanJinUnitExample example);

    int updateByExample(@Param("record") FdNanJinUnit record, @Param("example") FdNanJinUnitExample example);

    int updateByPrimaryKeySelective(FdNanJinUnit record);

    int updateByPrimaryKey(FdNanJinUnit record);
}