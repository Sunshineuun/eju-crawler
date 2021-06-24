package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanGtz;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanGtzExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 国土证Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanGtzMapper {
    long countByExample(FdWuhanGtzExample example);

    int deleteByExample(FdWuhanGtzExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdWuhanGtz record);

    int insertSelective(FdWuhanGtz record);

    List<FdWuhanGtz> selectByExample(FdWuhanGtzExample example);

    FdWuhanGtz selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdWuhanGtz record, @Param("example") FdWuhanGtzExample example);

    int updateByExample(@Param("record") FdWuhanGtz record, @Param("example") FdWuhanGtzExample example);

    int updateByPrimaryKeySelective(FdWuhanGtz record);

    int updateByPrimaryKey(FdWuhanGtz record);
}