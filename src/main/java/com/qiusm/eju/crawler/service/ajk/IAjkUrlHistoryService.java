package com.qiusm.eju.crawler.service.ajk;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.ajk.AjkUrlHistory;

/**
 * @author qiushengming
 */
public interface IAjkUrlHistoryService
        extends IService<AjkUrlHistory> {
    AjkUrlHistory getAjkHistoryByUrl(String url);

    void upHis(AjkUrlHistory his);

    void urlToBase64();
}
