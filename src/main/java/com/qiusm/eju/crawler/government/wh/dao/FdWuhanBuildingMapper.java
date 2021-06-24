package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanBuilding;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanBuildingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 楼栋Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanBuildingMapper {
    long countByExample(FdWuhanBuildingExample example);

    int deleteByExample(FdWuhanBuildingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FdWuhanBuilding record);

    int insertSelective(FdWuhanBuilding record);

    List<FdWuhanBuilding> selectByExample(FdWuhanBuildingExample example);

    FdWuhanBuilding selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FdWuhanBuilding record, @Param("example") FdWuhanBuildingExample example);

    int updateByExample(@Param("record") FdWuhanBuilding record, @Param("example") FdWuhanBuildingExample example);

    int updateByPrimaryKeySelective(FdWuhanBuilding record);

    int updateByPrimaryKey(FdWuhanBuilding record);

    /**
     * 查询哪些未完成的加载房屋信息的楼栋列表 <br>
     * 查询当前楼栋id向后的1000个楼栋 <br>
     *
     * @param maxId 当前楼栋最大ID
     * @return 楼栋列表
     */
    List<FdWuhanBuilding> selectByPendLoadHouseInfo(Long maxId);

    /**
     * 查询哪些未完成加载房屋详情的楼栋列表 <br>
     *
     * @param maxId 当前楼栋最大ID
     * @return 楼栋列表
     */
    List<FdWuhanBuilding> selectByPendLoadHouseDetail(Long maxId);
}