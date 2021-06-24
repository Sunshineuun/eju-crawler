package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanKfs;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanKfsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开发商Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanKfsMapper {
    long countByExample(FdWuhanKfsExample example);

    int deleteByExample(FdWuhanKfsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdWuhanKfs record);

    int insertSelective(FdWuhanKfs record);

    List<FdWuhanKfs> selectByExample(FdWuhanKfsExample example);

    FdWuhanKfs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdWuhanKfs record, @Param("example") FdWuhanKfsExample example);

    int updateByExample(@Param("record") FdWuhanKfs record, @Param("example") FdWuhanKfsExample example);

    int updateByPrimaryKeySelective(FdWuhanKfs record);

    int updateByPrimaryKey(FdWuhanKfs record);
}