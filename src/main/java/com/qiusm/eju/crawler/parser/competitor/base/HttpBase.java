package com.qiusm.eju.crawler.parser.competitor.base;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.DateUtils;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ImageReaderUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static com.qiusm.eju.crawler.constant.CrawlerDataPathConstant.SOURCE_LOG;

@Slf4j
public abstract class HttpBase implements IHttpSearch {

    private final OkHttpUtils httpClient = OkHttpUtils.Builder()
            .proxyUrl("http://crawler-ipproxy.ejudata.com/get/ip-list/13?key=4CZ5VH3TZSEYARFRSOVNLBOBH9NF6LW6XG5TADZS4LE=")
            .addProxyRetryTag(getProxyRetryTag())
            .builderHttp();

    /**
     * url构建的逻辑，由子类实现
     *
     * @param requestDto 请求dto
     */
    protected abstract void buildingUrl(RequestDto requestDto);

    /**
     * 解析响应结果
     *
     * @param requestDto  request
     * @param responseDto response
     */
    protected abstract void parser(RequestDto requestDto, ResponseDto responseDto);


    public OkHttpUtils getHttpClient() {
        return httpClient;
    }

    /**
     * 整体流程的管控
     *
     * @param requestDto 关键字
     * @return BkResponseDto response
     */
    @Override
    public ResponseDto execute(RequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
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
            FileUtils.printFile(msg, SOURCE_LOG + getSourceType(), this.getClass().getSimpleName() + ".log", true);
            requestDto.setResponseStr(e.getMessage());
        }
        //7. 判断结果状态
        if (!responseDto.isResultEmpty()) {
            responseDto.setSuccess(true);
        } else {
            responseDto.setSuccess(false);
            responseDto.setSysErrorMsg(requestDto.getResponseStr());
            responseDto.setResult(new JSONObject());
            responseDto.setBkErrorMsg(parserErrorMsg(requestDto));
        }
        return responseDto;
    }

    protected abstract String getSourceType();

    /**
     * 检查参数是否符合要求
     *
     * @param requestDto dto
     */
    protected void checkRequestParam(RequestDto requestDto) {

    }

    /**
     * 当请求失败的时候会尝试进行解析错误
     *
     * @param requestDto dto
     * @return String
     */
    protected String parserErrorMsg(RequestDto requestDto) {
        return requestDto.getResponseStr();
    }

    /**
     * 构建请求头
     *
     * @param dto dto
     */
    protected void buildingHeader(RequestDto dto) {
    }

    /**
     * 构建cookie
     *
     * @param dto dto
     */
    protected void buildingCookie(RequestDto dto) {
    }


    /**
     * 1.发送请求钱先去数据库中查找下，是否有相应的结果，如果有，就使用数据库中的；
     * 2.如果查找的是空的那么删除再进行请求
     * 3.请求成功后进行存储，存储之前判断结果是否为空的，为空则不进行存储
     *
     * @param requestDto requestDto
     */
    protected void httpGet(RequestDto requestDto) {
        httpGetA(requestDto);
    }

    /**
     * 通过httpclinet获取请求
     */
    protected void httpGetA(RequestDto requestDto) {
        String htmlStr = null;
        byte[] imgByte = null;
        switch (requestDto.getRequestMethod()) {
            case PROXY_GET:
                htmlStr = getHttpClient().proxyGet(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead());
                break;
            case IMG:
                imgByte = ImageReaderUtils.imageToByteV2(requestDto.getUrl(), requestDto.getHead());
                break;
            case POST_FORM:
                htmlStr = getHttpClient().postFrom(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead(), requestDto.getRequestParam());
                break;
            case PROXY_POST_JSON:
                htmlStr = getHttpClient().proxyPostJson(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead(), JSONObject.toJSONString(requestDto.getRequestParam()));
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

    protected boolean viewCheck(RequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        return !(StringUtils.isBlank(responseStr)
                || responseStr.startsWith("ejuResponseCode")
                || responseStr.startsWith("ResponseError")
                || responseStr.startsWith("ResponseCode"));
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
