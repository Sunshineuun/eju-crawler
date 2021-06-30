package com.qiusm.eju.crawler.competitor.beike.dao;

import com.qiusm.eju.crawler.competitor.beike.entity.BkFence;
import com.qiusm.eju.crawler.competitor.beike.entity.BkFenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BkFenceMapper {
    long countByExample(BkFenceExample example);

    int deleteByExample(BkFenceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BkFence record);

    int insertSelective(BkFence record);

    List<BkFence> selectByExampleWithBLOBs(BkFenceExample example);

    List<BkFence> selectByExample(BkFenceExample example);

    BkFence selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BkFence record, @Param("example") BkFenceExample example);

    int updateByExampleWithBLOBs(@Param("record") BkFence record, @Param("example") BkFenceExample example);

    int updateByExample(@Param("record") BkFence record, @Param("example") BkFenceExample example);

    int updateByPrimaryKeySelective(BkFence record);

    int updateByPrimaryKeyWithBLOBs(BkFence record);

    int updateByPrimaryKey(BkFence record);
}