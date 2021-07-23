package com.qiusm.eju.crawler.task.dao;

import com.qiusm.eju.crawler.task.entity.CrawlerTask;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlerTaskMapper {
    long countByExample(CrawlerTaskExample example);

    int deleteByExample(CrawlerTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrawlerTask record);

    int insertSelective(CrawlerTask record);

    List<CrawlerTask> selectByExample(CrawlerTaskExample example);

    CrawlerTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrawlerTask record, @Param("example") CrawlerTaskExample example);

    int updateByExample(@Param("record") CrawlerTask record, @Param("example") CrawlerTaskExample example);

    int updateByPrimaryKeySelective(CrawlerTask record);

    int updateByPrimaryKey(CrawlerTask record);
}