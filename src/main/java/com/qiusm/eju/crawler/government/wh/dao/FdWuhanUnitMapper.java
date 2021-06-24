package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanUnit;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanUnitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 房屋Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanUnitMapper {
    long countByExample(FdWuhanUnitExample example);

    int deleteByExample(FdWuhanUnitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdWuhanUnit record);

    int insertSelective(FdWuhanUnit record);

    List<FdWuhanUnit> selectByExample(FdWuhanUnitExample example);

    FdWuhanUnit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdWuhanUnit record, @Param("example") FdWuhanUnitExample example);

    int updateByExample(@Param("record") FdWuhanUnit record, @Param("example") FdWuhanUnitExample example);

    int updateByPrimaryKeySelective(FdWuhanUnit record);

    int updateByPrimaryKey(FdWuhanUnit record);
}