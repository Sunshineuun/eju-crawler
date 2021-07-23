package com.qiusm.eju.crawler.competitor.beike.service;

import com.qiusm.eju.crawler.competitor.beike.dao.BkDealUrlHistoryMapper;
import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistory;
import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistoryExample;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkDealUrlHistoryService {

    @Resource
    private BkDealUrlHistoryMapper historyMapper;

    public BkDealUrlHistory getBkHistoryByUrl(String url) {
        BkDealUrlHistoryExample example = new BkDealUrlHistoryExample();
        example.createCriteria().andUrlBase64EqualTo(BeikeUtils.authorization(url));
        List<BkDealUrlHistory> histories = historyMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(histories)) {
            return histories.get(0);
        }
        return null;
    }

    public void upHis(BkDealUrlHistory his) {
        his.setCreateTime(new Date());
        if (his.getId() == null) {
            historyMapper.insert(his);
        } else {
            historyMapper.updateByPrimaryKeyWithBLOBs(his);
        }
    }

    public void urlToBase64() {
        Map<String, String> clazz = new LinkedHashMap<>();
        clazz.put("BkAppCityDictSearch", "/dict/city?city_id=");
        clazz.put("BkAppCommunityPageListSearch", "/house/community/searchV2?limit_offset=0");
        clazz.put("BkAppCommunityListSearch", "/house/community/searchV2?limit_offset=");
        clazz.put("BkAppCommunityDetailSearch", "/house/resblock/detailpart1?id=");
        clazz.put("BkAppDealPageListSearch", "/house/chengjiao/searchV2?limit_offset=0");
        clazz.put("BkAppDealListSearch", "/house/chengjiao/searchV2?limit_offset=");
        clazz.put("BkAppDealDetailSearch", "/house/chengjiao/detailpart1?house_code=");
        clazz.put("BkAppDealDetailPartSearch", "/house/house/moreinfo?house_code=");
        clazz.put("UnitSearchV1", "/yezhu/publish/getUnits?building_id=");
        clazz.put("HouseSearchV1", "/yezhu/publish/getHouses?unit_id=");
        clazz.put("BuildingSearchV1", "/yezhu/publish/getBuildings?community_id=");

        Long step = 10000L;
        Long start = 1L;
        int index = 0;
        while (true) {
            BkDealUrlHistoryExample example = new BkDealUrlHistoryExample();
            example.createCriteria().andIdBetween(start, start + step)
                    .andClassHandlerIsNull();
            List<BkDealUrlHistory> list = historyMapper.selectByExample(example);

            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            for (BkDealUrlHistory var : list) {
                var.setUrlBase64(BeikeUtils.authorization(var.getUrl()));
                clazz.forEach((k, v) -> {
                    if (StringUtils.isBlank(var.getClassHandler())
                            && StringUtils.contains(var.getUrl(), v)) {
                        var.setClassHandler(k);
                    }
                });

                historyMapper.updateByPrimaryKey(var);
                if (index++ % 100 == 0) {
                    log.info("已经更新的数据量：{}", index);
                }
            }
            start += step;
        }
    }
}
