package com.qiusm.eju.crawler.competitor.beike;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.bk.BkDealUrlHistory;
import com.qiusm.eju.crawler.entity.bk.BkUrlHistory;
import com.qiusm.eju.crawler.service.bk.IBkDealUrlHistoryService;
import com.qiusm.eju.crawler.service.bk.IBkUrlHistoryService;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * url历史处理
 */
@Slf4j
@SpringBootTest
public class UrlHis {

    ThreadPoolExecutor executor = ThreadPoolUtils.newFixedThreadPool("bk-his-url", 4, 20L);
    @Resource
    private IBkDealUrlHistoryService bkDealUrlHistoryService;
    @Autowired
    protected IBkUrlHistoryService historyService;

    @Test
    void a() {
        String url = "BkAppDealDetailSearch,BkAppDealListSearch,BkAppDealPageListSearch";
        List<String> urlList = Arrays.asList(url.split(","));

        long step = 20000L;
        long start = 1L;
        int index = 0;

        while (true) {
            EntityWrapper<BkUrlHistory> entityWrapper = new EntityWrapper<>();
            entityWrapper.between("id", start, start + step);
            List<BkUrlHistory> list = new ArrayList<>();
            historyService.selectList(entityWrapper);

            if (CollectionUtils.isEmpty(list)) {
                break;
            }

            for (BkUrlHistory var : list) {
                executor.submit(() -> {
                    if (urlList.contains(var.getClassHandler())) {
                        // 如果是，在成交历史中增加数据，且删除总的历史中
                        BkDealUrlHistory dealUrlHistory = new BkDealUrlHistory();
                        dealUrlHistory.setUrl(var.getUrl());
                        dealUrlHistory.setUrlBase64(var.getUrlBase64());
                        dealUrlHistory.setCity(var.getCity());
                        dealUrlHistory.setClassHandler(var.getClassHandler());
                        dealUrlHistory.setIsSuccess(var.getIsSuccess());
                        dealUrlHistory.setResult(var.getResult());
                        dealUrlHistory.setCreateTime(new Date());
                        dealUrlHistory.insert();

                        bkDealUrlHistoryService.deleteById(var.getId());
                    }
                });

                if (index++ % 10000 == 0) {
                    log.info("已经更新的数据量：{}", index);
                }
            }
            start += step;
        }

    }

    @Test
    void b() {
        long step = 4000L;
        long start = 6001L;

        while (true) {
            EntityWrapper<BkDealUrlHistory> entityWrapper = new EntityWrapper<>();
            entityWrapper.gt("id", start)
                    .last("limit " + step);
            final List<BkDealUrlHistory> list = bkDealUrlHistoryService.selectList(entityWrapper);

            if (CollectionUtils.isEmpty(list)) {
                break;
            }

            for (BkDealUrlHistory var : list) {
                var.setResult(StringUtils.gzip(var.getResult()));
            }
            bkDealUrlHistoryService.updateBatchById(list);
            if (start % 1000 == 0) {
                log.info("已经更新的数据量：{}", start);
            }
            start += step;
        }
    }


}
