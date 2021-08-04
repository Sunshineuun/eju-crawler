package com.qiusm.eju.crawler.parser.poi.gaode;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.task.CrawlerUrlHistory;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.parser.competitor.base.HttpBase;
import com.qiusm.eju.crawler.service.task.ICrawlerUrlHistoryService;

import javax.annotation.Resource;

public abstract class GaodeBaseSearch
        extends HttpBase {

    @Resource
    private ICrawlerUrlHistoryService historyService;

    @Override
    protected String getSourceType() {
        return SourceTypeEnum.GAO_DE.getCode();
    }

    @Override
    protected void httpGet(RequestDto requestDto) {
        CrawlerUrlHistory his = historyService.getByUrl(requestDto.getUrl());

        if (his != null) {
            requestDto.setResponseStr(his.getResult());
        } else {
            his = new CrawlerUrlHistory();
        }

        if (!viewCheck(requestDto)) {
            httpGetA(requestDto);
            his.setResult(requestDto.getResponseStr());
            his.setUrl(requestDto.getUrl());
            his.setClassHandler(this.getClass().getSimpleName());
            his.setSource(SourceTypeEnum.GAO_DE.getCode());
            his.setIsSuccess(viewCheck(requestDto) ? 1 : 0);
            his.setTaskId(0L);
            historyService.upHis(his);
        }
    }
}
