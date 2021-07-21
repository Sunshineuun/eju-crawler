package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.competitor.beike.dao.BkDealUrlHistoryMapper;
import com.qiusm.eju.crawler.competitor.beike.entity.BkDealUrlHistory;
import com.qiusm.eju.crawler.competitor.beike.service.BkDealUrlHistoryService;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;
import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.LIANJIA_IM_VERSION;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.CONNECTION;

/**
 * @author qiushengming
 */
public abstract class BkAppDealBaseSearch extends BkAppBaseSearch {

    @Autowired
    protected BkDealUrlHistoryService historyService;

    @Override
    protected void buildingHeader(BkRequestDto dto) {
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

    protected boolean checkJsonError(JSONObject jsonObject) {
        return jsonObject != null && jsonObject.containsKey("errno")
                && StringUtils.equals(jsonObject.getString("errno"), "0")
                && jsonObject.containsKey("data")
                && jsonObject.get("data") != null;
    }

}