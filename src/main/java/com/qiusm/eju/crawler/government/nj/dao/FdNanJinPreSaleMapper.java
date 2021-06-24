package com.qiusm.eju.crawler.government.nj.dao;

import com.qiusm.eju.crawler.government.nj.entity.FdNanJinPreSale;
import com.qiusm.eju.crawler.government.nj.entity.FdNanJinPreSaleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FdNanJinPreSaleMapper {
    long countByExample(FdNanJinPreSaleExample example);

    int deleteByExample(FdNanJinPreSaleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdNanJinPreSale record);

    int insertSelective(FdNanJinPreSale record);

    List<FdNanJinPreSale> selectByExample(FdNanJinPreSaleExample example);

    FdNanJinPreSale selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdNanJinPreSale record, @Param("example") FdNanJinPreSaleExample example);

    int updateByExample(@Param("record") FdNanJinPreSale record, @Param("example") FdNanJinPreSaleExample example);

    int updateByPrimaryKeySelective(FdNanJinPreSale record);

    int updateByPrimaryKey(FdNanJinPreSale record);
}