package com.qiusm.eju.crawler.task.dao;

import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstance;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlerTaskInstanceMapper {
    long countByExample(CrawlerTaskInstanceExample example);

    int deleteByExample(CrawlerTaskInstanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrawlerTaskInstance record);

    int insertSelective(CrawlerTaskInstance record);

    List<CrawlerTaskInstance> selectByExample(CrawlerTaskInstanceExample example);

    CrawlerTaskInstance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrawlerTaskInstance record, @Param("example") CrawlerTaskInstanceExample example);

    int updateByExample(@Param("record") CrawlerTaskInstance record, @Param("example") CrawlerTaskInstanceExample example);

    int updateByPrimaryKeySelective(CrawlerTaskInstance record);

    int updateByPrimaryKey(CrawlerTaskInstance record);
}