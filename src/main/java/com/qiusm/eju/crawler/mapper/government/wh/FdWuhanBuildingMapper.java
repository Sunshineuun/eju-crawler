package com.qiusm.eju.crawler.mapper.government.wh;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qiusm.eju.crawler.entity.government.wh.FdWuhanBuilding;

import java.util.List;

/**
 * 楼栋Mapper
 *
 * @author qiushengming
 */
public interface FdWuhanBuildingMapper
        extends BaseMapper<FdWuhanBuilding> {
    List<FdWuhanBuilding> selectByPendLoadHouseInfo(Long maxId);


    List<FdWuhanBuilding> selectByPendLoadHouseDetail(Long maxId);
}