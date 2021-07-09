package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.competitor.beike.utils.BeikeUtils;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.ExceptionUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;

/**
 * @author qiushengming
 */
@Slf4j
public abstract class BkBaseSearch implements HttpSearch {

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
     * @param responseDto resonse
     */
    protected abstract void parser(BkRequestDto requestDto, BkResponseDto responseDto);

    /**
     * 整体流程的管控
     *
     * @param requestDto 关键字
     * @return BkResponseDto response
     */
    @Override
    public BkResponseDto search(BkRequestDto requestDto) {
        //1. 构建URL
        buildingUrl(requestDto);
        //2. 构建请求头
        buildingHeader(requestDto);
        BkResponseDto responseDto = new BkResponseDto();
        try {
            //3. 发送请求
            httpGet(requestDto);
            //4. 判断结果是否符合期望
            if (viewCheck(requestDto)) {
                //5. 解析结果httpGet
                parser(requestDto, responseDto);
            }
        } catch (Exception e) {
            log.error("{}\n{}\n{}", e.getMessage(), requestDto, responseDto);
            requestDto.setResponseStr(ExceptionUtils.stackTraceInfoToStr(e));
        }
        //6. 判断结果状态
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

    protected String parserBkErrorMsg(BkRequestDto requestDto) {
        JSONObject var = JSONObject.parseObject(requestDto.getResponseStr());
        return var.getString("error");
    }

    /**
     * 通过httpclinet获取请求
     */
    protected void httpGet(BkRequestDto requestDto) {
        String htmlStr = httpClient.get(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead());
        requestDto.setResponseStr(htmlStr);
    }

    protected boolean viewCheck(BkRequestDto requestDto) {
        String responseStr = requestDto.getResponseStr();
        return !(StringUtils.isBlank(responseStr)
                || responseStr.startsWith("ejuResponseCode")
                || responseStr.startsWith("ResponseError")
                || responseStr.startsWith("ResponseCode"));
    }

    protected void buildingHeader(BkRequestDto dto) {
        Map<String, String> head = dto.getHead();
        head.put("Authorization", BeikeUtils.authorization(dto.getUrl()));
        head.put("Lianjia-City-Id", dto.getRequestParam().get("city_id"));
        head.put("Cookie", "lianjia_udid=330000000102112;lianjia_token=2.00111d15bb6ba8dcdc00b03c8a0840afd5;lianjia_ssid=e06aa3a4-c605-4bb8-98e2-102bdfffe282;lianjia_uuid=4abc2f2a-87cd-432d-8f06-d800be577cde");

        head.put("Accept", "application/json");
        head.put("Accept-Encoding", "utf-8");
        head.put("Referer", "ershoulistsearch");
        head.put("User-Agent", "Beike2.20.1;google Pixel; Android 8.1.0");
        head.put("Lianjia-Channel", "Android_ke_wandoujia");
        head.put("Lianjia-Version", "2.20.1");
        head.put("Lianjia-Im-Version", "2.34.0");
        head.put("Host", "app.api.ke.com");
        head.put("Connection", "Keep-Alive");

        if (dto.getIsLoad()) {
            buildingCookie(dto);
        }
    }

    protected void buildingCookie(BkRequestDto dto) {

    }
}
