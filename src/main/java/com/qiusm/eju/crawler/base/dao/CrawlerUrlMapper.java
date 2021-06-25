package com.qiusm.eju.crawler.base.dao;

import com.qiusm.eju.crawler.base.entity.CrawlerUrl;
import com.qiusm.eju.crawler.base.entity.CrawlerUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlerUrlMapper {
    long countByExample(CrawlerUrlExample example);

    int deleteByExample(CrawlerUrlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrawlerUrl record);

    int insertSelective(CrawlerUrl record);

    List<CrawlerUrl> selectByExample(CrawlerUrlExample example);

    CrawlerUrl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrawlerUrl record, @Param("example") CrawlerUrlExample example);

    int updateByExample(@Param("record") CrawlerUrl record, @Param("example") CrawlerUrlExample example);

    int updateByPrimaryKeySelective(CrawlerUrl record);

    int updateByPrimaryKey(CrawlerUrl record);
}