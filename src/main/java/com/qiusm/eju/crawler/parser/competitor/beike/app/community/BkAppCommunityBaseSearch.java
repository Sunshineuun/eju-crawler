package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.*;
import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.LIANJIA_IM_VERSION;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.CONNECTION;

/**
 * @author qiushengming
 */
public abstract class BkAppCommunityBaseSearch extends BkAppBaseSearch {

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
}
