package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanHouse;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanHouseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanHouseMapper {
    long countByExample(FdWuhanHouseExample example);

    int deleteByExample(FdWuhanHouseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdWuhanHouse record);

    int insertSelective(FdWuhanHouse record);

    List<FdWuhanHouse> selectByExample(FdWuhanHouseExample example);

    FdWuhanHouse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdWuhanHouse record, @Param("example") FdWuhanHouseExample example);

    int updateByExample(@Param("record") FdWuhanHouse record, @Param("example") FdWuhanHouseExample example);

    int updateByPrimaryKeySelective(FdWuhanHouse record);

    int updateByPrimaryKey(FdWuhanHouse record);

    long selectMaxId();
}