package com.qiusm.eju.crawler.parser.competitor.beike.app;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.ImageReaderUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.xiaoleilu.hutool.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;
import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class BkAppBaseSearch implements HttpSearch {

    protected static final String CITY_ID = "city_id";

    protected static final String DOMAIN_NAME = "https://app.api.ke.com";

    protected OkHttpUtils httpClient = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL0)
            .addProxyRetryTag("ejuResponseCode")
            .builderHttp();

    /**
     * url构建的逻辑，由子类实现
     *
     * @param requestDto 请求dto
     */
    protected abstract void buildingUrl(BkRequestDto requestDto);

    /**
     * 解析响应结果
     *
     * @param requestDto  request
     * @param responseDto response
     */
    protected abstract void parser(BkRequestDto requestDto, BkResponseDto responseDto);

    /**
     * 整体流程的管控
     *
     * @param requestDto 关键字
     * @return BkResponseDto response
     */
    @Override
    public BkResponseDto execute(BkRequestDto requestDto) {
        BkResponseDto responseDto = new BkResponseDto();
        try {
            //0. 检验主要参数
            checkRequestParam(requestDto);
            //1. 构建URL
            buildingUrl(requestDto);
            //2. 构建请求头
            buildingHeader(requestDto);

            //3. 检查是否需要登录
            buildingCookie(requestDto);

            //4. 发送请求
            httpGet(requestDto);
            //5. 判断结果是否符合期望
            if (viewCheck(requestDto)) {
                //6. 解析结果httpGet
                parser(requestDto, responseDto);
            } else {
                throw new BusinessException("响应体不合法");
            }
        } catch (Exception e) {
            log.error("{},{}", e.getMessage(), requestDto.getUrl());
            String msg = String.format("%s\n%s\n%s\n%s\n\n\n\n", e.getMessage(), requestDto, responseDto, StringUtils.stackTraceInfoToStr(e));
            FileUtils.printFile(msg, "source\\beike\\logs\\", "bk_msg.log", true);
            requestDto.setResponseStr(e.getMessage());
        }
        //7. 判断结果状态
        if (!responseDto.isResultEmpty()) {
            responseDto.setSuccess(true);
        } else {
            responseDto.setSuccess(false);
            responseDto.setSysErrorMsg(requestDto.getResponseStr());
            responseDto.setResult(new JSONObject());
            responseDto.setBkErrorMsg(parserBkErrorMsg(requestDto));
        }
        return responseDto;
    }

    protected void checkRequestParam(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (requestParam.containsKey(CITY_ID)) {
            String mpn = requestParam.get(CITY_ID).trim();
            // 手机号码不为空 && 手机号码必须等于12位
            if (!(StringUtils.isNotBlank(mpn) && StringUtils.length(mpn) == 6)) {
                throw new BusinessException("手机号码验证不通过. {}", mpn);
            }
        }
    }

    protected String parserBkErrorMsg(BkRequestDto requestDto) {
        if (StringUtils.isNotBlank(requestDto.getResponseStr())) {
            JSONObject var = JSONObject.parseObject(requestDto.getResponseStr());
            return var.getString("error");
        }
        return null;
    }

    /**
     * 通过httpclinet获取请求
     */
    protected void httpGet(BkRequestDto requestDto) {
        String htmlStr = null;
        byte[] imgByte = null;
        switch (requestDto.getRequestMethod()) {
            case GET:
                htmlStr = httpClient.get(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead());
                break;
            case IMG:
                imgByte = ImageReaderUtils.imageToByteV2(requestDto.getUrl(), requestDto.getHead());
                break;
            case POST_FORM:
                htmlStr = httpClient.postFrom(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead(), requestDto.getRequestParam());
                break;
            default:
                log.error("未知请求类型{}", requestDto.getRequestMethod());
        }
        if (imgByte != null) {
            requestDto.setResponseByte(imgByte);
        }

        if (htmlStr != null) {
            requestDto.setResponseStr(htmlStr);
        }
    }

    protected boolean viewCheck(BkRequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        return !(StringUtils.isBlank(responseStr)
                || responseStr.startsWith("ejuResponseCode")
                || responseStr.startsWith("ResponseError")
                || responseStr.startsWith("ResponseCode"));
    }

    protected void buildingHeader(BkRequestDto dto) {
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
            throw new BusinessException("请求头缺少【LIANJIA_CITY_ID】");
        }

        dto.setHead(baseHead);

    }

    protected void buildingCookie(BkRequestDto dto) {
        String cookie = String.format("lianjia_udid=330000000%s;lianjia_ssid=%s;lianjia_uuid=%s;",
                RandomUtil.randomNumbers(6), UUID.randomUUID(), UUID.randomUUID());
        if (dto.getIsLoad()) {
            if (StringUtils.isNotBlank(dto.getUser().getToken())) {
                cookie += "lianjia_token=" + dto.getUser().getToken();
            } else {
                throw new BusinessException("无效的cookie");
            }
        }
        dto.getHead().put(COOKIE, cookie);
    }
}
