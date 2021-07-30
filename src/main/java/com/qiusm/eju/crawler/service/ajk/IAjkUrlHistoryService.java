package com.qiusm.eju.crawler.service.ajk;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.ajk.AjkUrlHistory;

/**
 * @author qiushengming
 */
public interface IAjkUrlHistoryService
        extends IService<AjkUrlHistory> {
    AjkUrlHistory getAjkHistoryByUrl(RequestDto requestDto);

    void upHis(AjkUrlHistory his);

    void urlToBase64();
}
