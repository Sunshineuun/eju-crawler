package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;
import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.LIANJIA_CITY_ID;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.CONNECTION;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class BkAppSkeletonBaseSearch extends BkAppBaseSearch {

    @Override
    protected void buildingHeader(RequestDto dto) {
        Map<String, String> baseHead = new HashMap<>(16);
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
        if (dto.getUser() != null) {
            baseHead.put(LIANJIA_DEVICE_ID, dto.getUser().getDeviceId());
        }

        baseHead.putAll(dto.getHead());

        if (!baseHead.containsKey(LIANJIA_CITY_ID)) {
            throw new BusinessException(10000, "请求头缺少【LIANJIA_CITY_ID】");
        }

        dto.setHead(baseHead);

    }

    @Override
    protected boolean viewCheck(RequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        if (StringUtils.contains(responseStr, "请重新登录")) {
            requestDto.getUser().setState(99);
            log.warn("需要重新登录：{}", requestDto.getUser());
        }
        return super.viewCheck(requestDto);
    }
}
