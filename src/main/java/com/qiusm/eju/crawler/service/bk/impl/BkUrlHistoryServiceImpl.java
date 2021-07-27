package com.qiusm.eju.crawler.service.bk.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.bk.BkUrlHistory;
import com.qiusm.eju.crawler.mapper.bk.BkUrlHistoryMapper;
import com.qiusm.eju.crawler.service.bk.IBkUrlHistoryService;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkUrlHistoryServiceImpl
        extends ServiceImpl<BkUrlHistoryMapper, BkUrlHistory>
        implements IBkUrlHistoryService {


    private final ThreadPoolExecutor executor = ThreadPoolUtils.newFixedThreadPool("his-url", 2, 20L);

    @Override
    public BkUrlHistory getBkHistoryByUrl(String url) {
        EntityWrapper<BkUrlHistory> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("url_base64", BeikeUtils.toBase64(url));
        return this.selectOne(entityWrapper);
    }

    @Override
    public void upHis(BkUrlHistory his) {
        his.setCreateTime(new Date());
        if (his.getId() == null) {
            his.setUrlBase64(BeikeUtils.toBase64(his.getUrl()));
            this.insert(his);
        } else {
            this.updateById(his);
        }
    }

    @Override
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

        long step = 20000L;
        Long start = 1L;
        int index = 0;
        while (true) {
            EntityWrapper<BkUrlHistory> entityWrapper = new EntityWrapper<>();
            entityWrapper.between("id", start, start + step);
            List<BkUrlHistory> list = new ArrayList<>();
            // this.selectList(entityWrapper);

            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            for (BkUrlHistory var : list) {
                executor.submit(() -> {
                    var.setUrlBase64(BeikeUtils.toBase64(var.getUrl()));
                    clazz.forEach((k, v) -> {
                        if (StringUtils.isBlank(var.getClassHandler())
                                && StringUtils.contains(var.getUrl(), v)) {
                            var.setClassHandler(k);
                        }
                    });

                    this.updateById(var);
                });

                if (index++ % 10000 == 0) {
                    log.info("已经更新的数据量：{}", index);
                }
            }
            start += step;
        }
    }
}
