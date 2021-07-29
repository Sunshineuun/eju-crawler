package com.qiusm.eju.crawler.parser.competitor.beike.app;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.bk.BkUrlHistory;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.base.HttpBase;
import com.qiusm.eju.crawler.service.bk.IBkUrlHistoryService;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import com.xiaoleilu.hutool.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class BkAppBaseSearch
        extends HttpBase
        implements IHttpSearch {

    @Autowired
    protected IBkUrlHistoryService historyService;

    protected static final String CITY_ID = "city_id";

    protected static final String DOMAIN_NAME = "https://app.api.ke.com";

    @Override
    protected void checkRequestParam(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (requestParam.containsKey(CITY_ID)) {
            String mpn = requestParam.get(CITY_ID).trim();
            // 手机号码不为空 && 手机号码必须等于12位
            if (!(StringUtils.isNotBlank(mpn) && StringUtils.length(mpn) == 6)) {
                throw new BusinessException(10000, "手机号码验证不通过." + mpn);
            }
        }
    }

    @Override
    protected String parserErrorMsg(RequestDto requestDto) {
        if (viewCheck(requestDto)) {
            JSONObject var = JSONObject.parseObject(requestDto.getResponseStr());
            return var.getString("error");
        }
        return null;
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

    @Override
    protected boolean viewCheck(RequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        return super.viewCheck(requestDto)
                && !(responseStr.contains("<h1>人机认证</h1>")
                || responseStr.contains("<title>人机认证</title>"))
                &&
                (StringUtils.contains(responseStr, "\"errno\": 0,")
                        || StringUtils.contains(responseStr, "\"errno\":0,"));
    }

    protected boolean checkJsonError(JSONObject jsonObject) {
        return jsonObject != null && jsonObject.containsKey("errno")
                && StringUtils.equals(jsonObject.getString("errno"), "0")
                && jsonObject.containsKey("data")
                && jsonObject.get("data") != null;
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
        dto.setHead(baseHead);

    }

    @Override
    protected void buildingCookie(RequestDto dto) {
        String cookie = String.format("lianjia_udid=330000000%s;lianjia_ssid=%s;lianjia_uuid=%s;",
                RandomUtil.randomNumbers(6), UUID.randomUUID(), UUID.randomUUID());
        if (dto.getIsLoad()) {
            if (StringUtils.isNotBlank(dto.getUser().getToken())) {
                cookie += "lianjia_token=" + dto.getUser().getToken();
            } else {
                throw new BusinessException(10000, "无效的cookie");
            }
        }
        dto.getHead().put(COOKIE, cookie);
    }

    @Override
    protected String[] getProxyRetryTag() {
        String[] tags = new String[]{
                "<h1>人机认证</h1>", "<title>人机认证</title>"
        };
        return ArrayUtils.addAll(tags, super.getProxyRetryTag());
    }
}
