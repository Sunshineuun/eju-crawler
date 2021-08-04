package com.qiusm.eju.crawler.parser.competitor.beike.pc;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.bk.BkUrlHistory;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.parser.competitor.base.HttpBase;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.service.bk.IBkUrlHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BkPcBaseSearch
        extends HttpBase
        implements IHttpSearch {
    @Autowired
    protected IBkUrlHistoryService historyService;

    @Override
    protected String getSourceType() {
        return SourceTypeEnum.BK_PC.getCode();
    }

    /**
     * 1.发送请求钱先去数据库中查找下，是否有相应的结果，如果有，就使用数据库中的；
     * 2.如果查找的是空的那么删除再进行请求
     * 3.请求成功后进行存储，存储之前判断结果是否为空的，为空则不进行存储
     *
     * @param requestDto requestDto
     */
    protected void httpGet(RequestDto requestDto) {
        BkUrlHistory his = historyService.getBkHistoryByUrl(requestDto.getUrl());

        if (his != null) {
            requestDto.setResponseStr(his.getResult());
        } else {
            his = new BkUrlHistory();
        }

        if (!viewCheck(requestDto)) {
            httpGetA(requestDto);
            his.setResult(requestDto.getResponseStr());
            his.setUrl(requestDto.getUrl());
            his.setClassHandler(this.getClass().getSimpleName());

            his.setIsSuccess(viewCheck(requestDto) ? 1 : 0);
            historyService.upHis(his);
        }
    }
}
