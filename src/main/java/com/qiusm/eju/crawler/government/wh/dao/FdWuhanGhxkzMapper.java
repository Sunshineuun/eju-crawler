package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanGhxkz;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanGhxkzExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规划许可证Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanGhxkzMapper {
    long countByExample(FdWuhanGhxkzExample example);

    int deleteByExample(FdWuhanGhxkzExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdWuhanGhxkz record);

    int insertSelective(FdWuhanGhxkz record);

    List<FdWuhanGhxkz> selectByExample(FdWuhanGhxkzExample example);

    FdWuhanGhxkz selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdWuhanGhxkz record, @Param("example") FdWuhanGhxkzExample example);

    int updateByExample(@Param("record") FdWuhanGhxkz record, @Param("example") FdWuhanGhxkzExample example);

    int updateByPrimaryKeySelective(FdWuhanGhxkz record);

    int updateByPrimaryKey(FdWuhanGhxkz record);
}