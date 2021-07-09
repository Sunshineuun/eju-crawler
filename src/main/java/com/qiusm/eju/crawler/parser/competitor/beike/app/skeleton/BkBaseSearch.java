package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.qiusm.eju.crawler.competitor.beike.utils.BeikeUtils;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;

/**
 * @author qiushengming
 */
public abstract class BkBaseSearch implements HttpSearch {

    protected static final String DOMAIN_NAME = "https://app.api.ke.com";

    protected OkHttpUtils httpClient = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL0).charset(UTF8)
            .connectTimeout(60000).readTimeout(60000)
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
        //3. 发送请求
        httpGet(requestDto);
        //4. 解析结果httpGet
        BkResponseDto responseDto = new BkResponseDto();
        parser(requestDto, responseDto);
        return responseDto;
    }

    /**
     * 通过httpclinet获取请求
     */
    protected void httpGet(BkRequestDto requestDto) {
        String htmlStr = httpClient.get(requestDto.getUrl(), requestDto.getCharset(), requestDto.getHead());
        requestDto.setResponseStr(htmlStr);
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
    }
}
