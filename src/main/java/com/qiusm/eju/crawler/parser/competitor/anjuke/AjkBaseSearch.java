package com.qiusm.eju.crawler.parser.competitor.anjuke;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.app.HttpSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.DateUtils;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ImageReaderUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class AjkBaseSearch implements HttpSearch {

    protected OkHttpUtils httpClient = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL0)
            .addProxyRetryTag(getProxyRetryTag())
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
                log.error("{},{}", requestDto.getResponseStr(), requestDto.getUrl());
            }
        } catch (Exception e) {
            log.error("{},url:{}", e.getMessage(), requestDto.getUrl());
            String msg = String.format("%s\n%s\n%s\n%s\n%s\n\n\n\n", DateUtils.formatDateTime(new Date()),
                    e.getMessage(), requestDto, responseDto, StringUtils.stackTraceInfoToStr(e));
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

    protected void checkRequestParam(BkRequestDto requestDto) {}

    protected String parserBkErrorMsg(BkRequestDto requestDto) {
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
    protected void httpGet(BkRequestDto requestDto) {/*
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
    */}

    /**
     * 通过httpclinet获取请求
     */
    protected void httpGetA(BkRequestDto requestDto) {
        String htmlStr = null;
        byte[] imgByte = null;
        switch (requestDto.getRequestMethod()) {
            case GET:
                htmlStr = httpClient.proxyGet(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead());
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
        dto.setHead(baseHead);
    }

    protected void buildingCookie(BkRequestDto dto) {

    }

    /**
     * 重试判断标签
     *
     * @return 标签列表
     */
    protected String[] getProxyRetryTag() {
        return new String[]{"ejuResponseCode"};
    }
}
