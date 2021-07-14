package com.qiusm.eju.crawler.parser.competitor.beike.app.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.competitor.beike.utils.BeikeUtils;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import com.qiusm.eju.crawler.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.AUTHORIZATION;

/**
 * @author qiushengming
 */
public class LoginByPasswordV2 extends BkBaseSearch {
    private static final String URL_TEMPLATE = "https://app.api.ke.com/user/account/loginByPasswordV2";
    private static final String MOBILE_PHONE_NO = "mobile_phone_no";
    private static final String PIC_VERIFY_CODE = "pic_verify_code";
    private static final String PASSWORD = "password";
    private static final String REQUEST_TS = "request_ts";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        requestDto.setUrl(URL_TEMPLATE);
        requestDto.setRequestMethod(RequestMethodEnum.POST_FORM);

        Map<String, String> requestParam = requestDto.getRequestParam();
        requestParam.put(REQUEST_TS, Long.toString(System.currentTimeMillis()));
        requestParam.put(MOBILE_PHONE_NO, requestDto.getUser().getPhoneNo());
        requestParam.put(PASSWORD, requestDto.getUser().getPassword());

        String[] keys = new String[]{MOBILE_PHONE_NO, PASSWORD};
        for (String key : keys) {
            if (requestParam.containsKey(key)) {
                String value = requestParam.get(key).trim();
                if (!StringUtils.isNotBlank(value)) {
                    throw new BusinessException("关键数据存在为空。", key);
                }
            }
        }
    }

    /**
     * {"request_id":"6147b698-5057-41fc-8741-1fd86195d0f7","uniqid":"010ACA160EF1B4B5A37A01CDBB53BCCC","errno":0,
     * "error":"","data":{"access_token":"2.0015d34c9b6f6685fc047e65aafc1f3239","nick_name":"15****95",
     * "client_id":2000000001483915,"mobile":"15958624595","use_lj_im":1},"cost":235}
     *
     * @param requestDto  request
     * @param responseDto response
     */
    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject json = JSON.parseObject(requestDto.getResponseStr());
        String accessToken = JSONUtils.getStringByKey(json, "access_token");
        requestDto.getUser().setToken(accessToken);
        responseDto.setResult(json);
    }

    @Override
    protected void buildingHeader(BkRequestDto dto) {
        super.buildingHeader(dto);
        Map<String, String> baseHead = new HashMap<>(16);
        baseHead.put(AUTHORIZATION, BeikeUtils.authorization(dto.getUrl(), dto.getRequestParam()));
        dto.getHead().putAll(baseHead);
    }
}
