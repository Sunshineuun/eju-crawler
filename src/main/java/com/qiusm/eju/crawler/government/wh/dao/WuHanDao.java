package com.qiusm.eju.crawler.government.wh.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.government.wh.FdWuhanBuilding;
import com.qiusm.eju.crawler.entity.government.wh.FdWuhanUnit;
import com.qiusm.eju.crawler.mapper.government.wh.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class WuHanDao {

    @Resource
    private FdWuhanHouseMapper houseMapper;

    @Resource
    private FdWuhanGtzMapper gtzMapper;

    @Resource
    private FdWuhanGhxkzMapper ghxkzMapper;

    @Resource
    private FdWuhanKfsMapper kfsMapper;

    @Resource
    private FdWuhanBuildingMapper buildingMapper;

    @Resource
    private FdWuhanUnitMapper unitMapper;

    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<FdWuhanUnit> units) {
        units.forEach(o -> {
            unitMapper.insert(o);
        });
    }

    public Map<String, FdWuhanUnit> selectBuildingIdByHouseUnit(FdWuhanBuilding building) {
        // 查询需要进行处理的unitDb
        EntityWrapper<FdWuhanUnit> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("building_id", building.getId())
                .ne("status", "99")
                .isNotNull("details_url")
                .isNull("house_address");

        List<FdWuhanUnit> unitsDb = unitMapper.selectList(entityWrapper);
        Map<String, FdWuhanUnit> unitMapDb = new HashMap<>(unitsDb.size());
        unitsDb.forEach(o -> {
            String key = String.format("%s,%s,%s", o.getUnitId(), o.getNominalFloor(), o.getRoomNo());
            unitMapDb.put(key, o);
        });

        if (unitMapDb.size() != unitsDb.size()) {
            log.error("去重后。预期数量为：{},实际数量为：{}。两者不符。", unitsDb.size(), unitMapDb.size());
            return null;
        }

        return unitMapDb;
    }
}
