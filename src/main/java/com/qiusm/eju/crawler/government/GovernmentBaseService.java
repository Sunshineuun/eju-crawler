package com.qiusm.eju.crawler.government;

import com.qiusm.eju.crawler.constant.CharacterSet;
import com.qiusm.eju.crawler.entity.base.CrawlerUrl;
import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import com.qiusm.eju.crawler.service.base.ICrawlerUrlService;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class GovernmentBaseService {
    /**
     * 重试次数
     */
    private static final int TRY_NUM = 5;

    protected static final List<String> ERROR_MSG = new ArrayList<>();

    @Resource
    private ICrawlerUrlService crawlerUrlService;

    static {
        ERROR_MSG.addAll(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=".split(COMMA)));
    }

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

        CrawlerUrl crawlerUrl = new CrawlerUrl(requestUrl, type, success ? "1" : "0");
        crawlerUrl.insert();
        return success;
    }
}
