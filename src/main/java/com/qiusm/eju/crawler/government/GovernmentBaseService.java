package com.qiusm.eju.crawler.government;

import com.qiusm.eju.crawler.constant.CharacterSet;
import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class GovernmentBaseService {
    /**
     * 重试次数
     */
    private static final int TRY_NUM = 5;

    private static final String[] ERROR_MSG = "ejuResponseCode=500,ResponseCode=,ResponseError=,<h1>您的网络存在异常！</h1>".split(",");

    private final OkHttpUtils httpClient = CommonUtils.createHttpClient();

    protected final StopWatch sw = new StopWatch();

    protected String httpGetBody(String requestUrl, Map<String, String> heads, String type) {
        return httpGetBodyAll(requestUrl, heads, type, CharacterSet.GBK);
    }

    protected String httpGetBodyNoHead(String requestUrl, String type, String charset) {
        return httpGetBodyAll(requestUrl, new HashMap<>(8), type, charset);
    }

    /**
     * 需要增加异常校验，判断请求结果是否复合预期要求
     *
     * @param requestUrl 请求URL
     * @param params     请求参数
     * @return html
     */
    protected String httpPostBodyGbk(String requestUrl, Map<String, String> params, String type) {
        int tryCount = 0;
        while (tryCount++ < TRY_NUM) {
            String listBody = httpClient.proxyPostFrom(requestUrl, CharacterSet.GBK, null, params);
            if (checkBody(requestUrl, listBody, type)) {
                return listBody;
            }
        }
        return null;
    }

    protected String httpGetBodyAll(String requestUrl, Map<String, String> heads, String type, String charset) {
        int tryCount = 0;

        while (tryCount++ < TRY_NUM) {
            String listBody = httpClient.proxyGet(requestUrl, charset, heads);
            if (checkBody(requestUrl, listBody, type)) {
                return listBody;
            }
        }
        return null;
    }

    private boolean checkBody(String requestUrl, String htmlStr, String type) {
        log.debug("正在处理：{}", requestUrl);

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1) / 2);
        } catch (InterruptedException ignored) {
        }

        boolean success = true;

        for (String msg : ERROR_MSG) {
            if (StringUtils.startsWith(htmlStr, msg)) {
                success = false;
            }
        }

        return success;
    }
}
