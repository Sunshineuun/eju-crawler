package com.qiusm.eju.crawler.service.bk;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.bk.BkUrlHistory;

/**
 * @author qiushengming
 */
public interface IBkUrlHistoryService
        extends IService<BkUrlHistory> {
    BkUrlHistory getBkHistoryByUrl(String url);

    void upHis(BkUrlHistory his);

    void urlToBase64();
}
