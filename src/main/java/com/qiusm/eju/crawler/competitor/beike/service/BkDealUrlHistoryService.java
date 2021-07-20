package com.qiusm.eju.crawler.competitor.beike.service;

import com.qiusm.eju.crawler.competitor.beike.dao.BkDealUrlHistoryMapper;
import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistory;
import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistoryExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qiushengming
 */
@Service
public class BkDealUrlHistoryService {

    @Resource
    private BkDealUrlHistoryMapper historyMapper;

    public BkDealUrlHistory getHistoryByUrl(String url) {
        BkDealUrlHistoryExample example = new BkDealUrlHistoryExample();
        example.createCriteria().andUrlEqualTo(url);
        List<BkDealUrlHistory> histories = historyMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(histories)) {
            return histories.get(0);
        }
        return null;
    }

    public void upHis(BkDealUrlHistory his) {
        if (his.getId() == null) {
            historyMapper.insert(his);
        } else {
            historyMapper.updateByPrimaryKeyWithBLOBs(his);
        }
    }
}
