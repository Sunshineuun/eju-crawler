package com.qiusm.eju.crawler.parser.competitor.beike.app.login;

import com.qiusm.eju.crawler.competitor.beike.utils.BeikeUtils;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;

/**
 * @author qiushengming
 */
@Slf4j
public class LoginByVerifyCode extends BkBaseSearch {
    private static final String URL_TEMPLATE = "https://app.api.ke.com/user/account/loginbyverifycode";
    private static final String MOBILE_PHONE_NO = "mobile_phone_no";
    private static final String PIC_VERIFY_CODE = "pic_verify_code";
    private static final String VERIFY_CODE = "verify_code";
    private static final String REQUEST_TS = "request_ts";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        requestDto.setUrl(URL_TEMPLATE);
        requestDto.setRequestMethod(RequestMethodEnum.POST_FORM);

        Map<String, String> requestParam = requestDto.getRequestParam();
        requestParam.put(REQUEST_TS, Long.toString(System.currentTimeMillis()));
        requestParam.put(MOBILE_PHONE_NO, requestDto.getUser().getPhoneNo());

        String[] keys = new String[]{MOBILE_PHONE_NO, VERIFY_CODE};
        for (String key : keys) {
            if (requestParam.containsKey(key)) {
                String value = requestParam.get(key).trim();
                if (!StringUtils.isNotBlank(value)) {
                    throw new BusinessException("关键数据存在为空。", key);
                }
            }
        }
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        log.info("{}", requestDto);
    }

    @Override
    protected void buildingHeader(BkRequestDto dto) {
        super.buildingHeader(dto);
        Map<String, String> baseHead = new HashMap<>(16);
        baseHead.put(AUTHORIZATION, BeikeUtils.authorization(dto.getUrl(), dto.getRequestParam()));
        dto.getHead().putAll(baseHead);
    }
}
