package com.qiusm.eju.crawler.parser.competitor.anjuke.app;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.ajk.AjkUrlHistory;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.parser.competitor.base.HttpBase;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.service.ajk.IAjkUrlHistoryService;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class AjkAppBase
        extends HttpBase
        implements IHttpSearch {

    protected static final String DOMAIN = "https://m.anjuke.com/";

    @Resource
    private IAjkUrlHistoryService historyService;

    @Override
    protected String getSourceType() {
        return SourceTypeEnum.AJK_APP.getCode();
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
        Map<String, String> headers = new HashMap<>();
        headers.put("sec-fetch-mode", "cors");
//        headers.put("user-agent", "Mozilla/5.0 (Linux; Android 8.1.0; Pixel Build/OPM1.171019.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/77.0.3865.120 MQQBrowser/6.2 TBS/045618 Mobile Safari/537.36; AJK/15.13");
        headers.put("accept", "*/*");
        headers.put("x-requested-with", "com.anjuke.android.app");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("referer", "https://m.anjuke.com/yezhu/across/publish/form?entry=1&from=esfpd&os=android&city_id=11&is_dx=0&");
        headers.put("accept-encoding", "utf-8");
        headers.put("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("cookie", "ajkAuthTicket=TT=1db085a67e193b59be533ef79d661f18&TS=1626831270400&PBODY=oEqoI3BSEYPhccsPlyKSE8Eev9Qv_aAGhSr9XDQaYK4MTNIC1BdZmkuCq9SzDBpQhGN003J_eRS2sM9UWLYQWPRjQP9281NED39QkWIVA-vahdwa_S8FaZxnwJYjnJUM-gzYDgGMFUCTMZhZzqoEScgZXNqeNUuqQrWI6lcTV7c&VER=2;ajkTgc=TT=1db085a67e193b59be533ef79d661f18&TS=1626831270399&PBODY=PAPmOYkbj_vkV2KIgpqrWUP0tKLviizrChjimNpgvhzG3D62Sj7ewUIKY9Q4IRFCHJd552CBgcVaHNi7j0gDnQYLYdUaIv-FvcMYiLUR-XJTqUpBLG3ewUGqsCCSld0o4qZFwLWHOuZ9iLE07S1btOl74jG58alGmobMS7E5kN4&VER=2");
        dto.getHead().putAll(headers);
    }

    @Override
    protected boolean viewCheck(RequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        return super.viewCheck(requestDto)
                && (
                (StringUtils.contains(responseStr, "\"msg\":\"ok\",")
                        || StringUtils.contains(responseStr, "\"status\":\"0\","))
        );
    }

    @Override
    protected String[] getProxyRetryTag() {
        return super.getProxyRetryTag();
    }
}
