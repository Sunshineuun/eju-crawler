package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.bk.BkDealUrlHistory;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.service.bk.IBkDealUrlHistoryService;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.CITY_ID;
import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.*;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.CONNECTION;

/**
 * @author qiushengming
 */
public abstract class BkAppDealBaseSearch extends BkAppBaseSearch {

    @Resource
    private IBkDealUrlHistoryService bkDealUrlHistoryService;

    @Override
    protected void buildingHeader(RequestDto dto) {
        Map<String, String> baseHead = new HashMap<>(16);
        baseHead.putAll(dto.getHead());
        baseHead.put(AUTHORIZATION, BeikeUtils.authorization(dto.getUrl()));
        baseHead.put(ACCEPT, "application/json");
        baseHead.put(ACCEPT_ENCODING, "utf-8");
        baseHead.put(REFERER, "ershoulistsearch");
        baseHead.put(USER_AGENT, "Beike2.20.1;google Pixel; Android 8.1.0");
        baseHead.put(HOST, "app.api.ke.com");
        baseHead.put(CONNECTION, "Keep-Alive");
        baseHead.put(LIANJIA_CHANNEL, "Android_ke_wandoujia");
        baseHead.put(LIANJIA_VERSION, "2.20.1");
        baseHead.put(LIANJIA_IM_VERSION, "2.34.0");
        dto.setHead(baseHead);
    }

    protected void httpGet(RequestDto requestDto) {
        if (!requestDto.isLoadCache()) {
            httpGetA(requestDto);
            return;
        }

        BkDealUrlHistory his = bkDealUrlHistoryService.getBkHistoryByUrl(requestDto.getUrl());

        if (his != null) {
            requestDto.setResponseStr(his.getResult());
        } else {
            his = new BkDealUrlHistory();
        }


        if (!viewCheck(requestDto)) {
            long s = System.currentTimeMillis();
            httpGetA(requestDto);
            long e = System.currentTimeMillis();

            his.setResult(requestDto.getResponseStr());
            his.setUrl(requestDto.getUrl());
            his.setClassHandler(this.getClass().getSimpleName());
            his.setCity(requestDto.getRequestParam().get(CITY_ID));
            his.setTimeConsuming(e - s);
            his.setIsSuccess(viewCheck(requestDto) ? 1 : 0);
            bkDealUrlHistoryService.upHis(his);
        }

        if (StringUtils.isBlank(his.getCity())) {
            his.setCity(requestDto.getRequestParam().get(CITY_ID));
            bkDealUrlHistoryService.upHis(his);
        }
    }
}
