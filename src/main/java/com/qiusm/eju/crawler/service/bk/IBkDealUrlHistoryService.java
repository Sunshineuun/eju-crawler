package com.qiusm.eju.crawler.service.bk;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.bk.BkDealUrlHistory;

/**
 * @author qiushengming
 */
public interface IBkDealUrlHistoryService
        extends IService<BkDealUrlHistory> {
    BkDealUrlHistory getBkHistoryByUrl(String url);

    void upHis(BkDealUrlHistory his);
}
