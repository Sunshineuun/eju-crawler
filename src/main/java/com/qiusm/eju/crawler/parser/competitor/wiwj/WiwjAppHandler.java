package com.qiusm.eju.crawler.parser.competitor.wiwj;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.ajk.AjkUrlHistory;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.parser.competitor.base.HttpBase;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.service.ajk.IAjkUrlHistoryService;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * post表单提交模式
 *
 * @author qiushengming
 */
@Slf4j
public abstract class WiwjAppHandler
        extends HttpBase
        implements IHttpSearch {

    protected static final String DOMAIN = "https://appapi.5i5j.com/";

    @Resource
    private IAjkUrlHistoryService historyService;

    @Override
    protected String getSourceType() {
        return SourceTypeEnum.WIWJ_APP.getCode();
    }

    protected void httpGet(RequestDto requestDto) {
        AjkUrlHistory his = historyService.getAjkHistoryByUrl(requestDto);

        if (his != null) {
            requestDto.setResponseStr(his.getResult());
        } else {
            his = new AjkUrlHistory();
        }

        if (!viewCheck(requestDto)) {
            httpGetA(requestDto);
            his.setResult(requestDto.getResponseStr());
            his.setUrl(requestDto.getUrl());
            his.setClassHandler(this.getClass().getSimpleName());
            his.setParams(JSONObject.toJSONString(requestDto.getRequestParam()));
            his.setIsSuccess(viewCheck(requestDto) ? 1 : 0);
            historyService.upHis(his);
        }
    }

    @Override
    protected void buildingHeader(RequestDto dto) {
    }

    @Override
    protected boolean viewCheck(RequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        return super.viewCheck(requestDto)
                && (
                (StringUtils.contains(responseStr, "\"msg\":\"OK\",")
                        || StringUtils.contains(responseStr, "\"status\":\"200\","))
        );
    }

    @Override
    protected String[] getProxyRetryTag() {
        return super.getProxyRetryTag();
    }
}
