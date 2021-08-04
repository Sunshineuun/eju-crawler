package com.qiusm.eju.crawler.parser.competitor.beike.app.login;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL5;
import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.CONNECTION;

/**
 * @author qiushengming
 */
public abstract class BkAppLoginBase extends BkAppBaseSearch {

    private final OkHttpUtils httpClient = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL5)
            .addProxyRetryTag(getProxyRetryTag())
            .builderHttp();

    @Override
    public OkHttpUtils getHttpClient() {
        return httpClient;
    }

    @Override
    protected void httpGet(RequestDto requestDto) {
        httpGetA(requestDto);
    }

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
}
