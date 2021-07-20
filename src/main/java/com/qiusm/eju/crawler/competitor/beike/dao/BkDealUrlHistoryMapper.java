package com.qiusm.eju.crawler.competitor.beike.dao;

import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistory;
import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BkDealUrlHistoryMapper {
    long countByExample(BkDealUrlHistoryExample example);

    int deleteByExample(BkDealUrlHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BkDealUrlHistory record);

    int insertSelective(BkDealUrlHistory record);

    List<BkDealUrlHistory> selectByExampleWithBLOBs(BkDealUrlHistoryExample example);

    List<BkDealUrlHistory> selectByExample(BkDealUrlHistoryExample example);

    BkDealUrlHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BkDealUrlHistory record, @Param("example") BkDealUrlHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") BkDealUrlHistory record, @Param("example") BkDealUrlHistoryExample example);

    int updateByExample(@Param("record") BkDealUrlHistory record, @Param("example") BkDealUrlHistoryExample example);

    int updateByPrimaryKeySelective(BkDealUrlHistory record);

    int updateByPrimaryKeyWithBLOBs(BkDealUrlHistory record);

    int updateByPrimaryKey(BkDealUrlHistory record);
}