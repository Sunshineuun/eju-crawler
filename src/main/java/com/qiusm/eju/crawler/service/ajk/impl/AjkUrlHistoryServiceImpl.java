package com.qiusm.eju.crawler.service.ajk.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.ajk.AjkUrlHistory;
import com.qiusm.eju.crawler.mapper.ajk.AjkUrlHistoryMapper;
import com.qiusm.eju.crawler.service.ajk.IAjkUrlHistoryService;
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
public class AjkUrlHistoryServiceImpl
        extends ServiceImpl<AjkUrlHistoryMapper, AjkUrlHistory>
        implements IAjkUrlHistoryService {


    private final ThreadPoolExecutor executor = ThreadPoolUtils.newFixedThreadPool("ajk-his-url", 2, 20L);

    @Override
    public AjkUrlHistory getAjkHistoryByUrl(String url) {
        EntityWrapper<AjkUrlHistory> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("url_base64", BeikeUtils.toBase64(url));
        return this.selectOne(entityWrapper);
    }

    @Override
    public void upHis(AjkUrlHistory his) {
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
        clazz.put("AjkAppCityDictSearch", "/dict/city?city_id=");
        clazz.put("AjkAppCommunityPageListSearch", "/house/community/searchV2?limit_offset=0");
        clazz.put("AjkAppCommunityListSearch", "/house/community/searchV2?limit_offset=");
        clazz.put("AjkAppCommunityDetailSearch", "/house/resblock/detailpart1?id=");
        clazz.put("AjkAppDealPageListSearch", "/house/chengjiao/searchV2?limit_offset=0");
        clazz.put("AjkAppDealListSearch", "/house/chengjiao/searchV2?limit_offset=");
        clazz.put("AjkAppDealDetailSearch", "/house/chengjiao/detailpart1?house_code=");
        clazz.put("AjkAppDealDetailPartSearch", "/house/house/moreinfo?house_code=");
        clazz.put("UnitSearchV1", "/yezhu/publish/getUnits?building_id=");
        clazz.put("HouseSearchV1", "/yezhu/publish/getHouses?unit_id=");
        clazz.put("BuildingSearchV1", "/yezhu/publish/getBuildings?community_id=");

        long step = 20000L;
        Long start = 1L;
        int index = 0;
        while (true) {
            EntityWrapper<AjkUrlHistory> entityWrapper = new EntityWrapper<>();
            entityWrapper.between("id", start, start + step);
            List<AjkUrlHistory> list = new ArrayList<>();
            // this.selectList(entityWrapper);

            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            for (AjkUrlHistory var : list) {
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
