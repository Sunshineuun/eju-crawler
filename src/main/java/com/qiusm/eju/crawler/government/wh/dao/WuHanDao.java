package com.qiusm.eju.crawler.government.wh.dao;

import com.qiusm.eju.crawler.government.wh.entity.FdWuhanBuilding;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanUnit;
import com.qiusm.eju.crawler.government.wh.entity.FdWuhanUnitExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiushengming
 */
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
        FdWuhanUnitExample unitExample = new FdWuhanUnitExample();
        unitExample.createCriteria()
                .andBuildingIdEqualTo(building.getId())
                .andDetailsUrlIsNotNull()
                .andHouseAddressIsNull();
        List<FdWuhanUnit> unitsDb = unitMapper.selectByExample(unitExample);
        Map<String, FdWuhanUnit> unitMapDb = new HashMap<>(unitsDb.size());
        unitsDb.forEach(o -> {
            String key = String.format("%s,%s,%s", o.getUnitId(), o.getNominalFloor(), o.getRoomNo());
            unitMapDb.put(key, o);
        });

        if (unitMapDb.size() != unitsDb.size()) {
            return null;
        }

        return unitMapDb;
    }
}
