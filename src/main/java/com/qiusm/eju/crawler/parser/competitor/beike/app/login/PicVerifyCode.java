package com.qiusm.eju.crawler.parser.competitor.beike.app.login;

import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.*;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.CONNECTION;
import static com.qiusm.eju.crawler.constant.head.HttpHeadConstant.HOST;

/**
 * 图形验证码获取校验
 * 面向URL:/user/VerifyCode/GeneratePicture?device_id={}&radom={}
 *
 * @author qiushengming
 */
@Service
public class PicVerifyCode extends BkAppLoginBase {
    private static final String URL_TEMPLATE = "%s/user/VerifyCode/GeneratePicture?device_id=%s&radom=%s";
    private static final String DEVICE_ID = "device_id";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();

        requestParam.put(DEVICE_ID, requestDto.getUser().getDeviceId());
        if (StringUtils.isNotBlank(requestDto.getUser().getDeviceId())) {
            String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestDto.getUser().getDeviceId(), System.currentTimeMillis());
            requestDto.setUrl(url);
        } else {
            throw new BusinessException(10000, "请求体缺失关键参数{deviceId}.");
        }

        requestDto.setRequestMethod(RequestMethodEnum.IMG);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        responseDto.setResultByte(requestDto.getResponseByte());
    }

    @Override
    protected void buildingHeader(RequestDto dto) {
        Map<String, String> baseHead = new HashMap<>(16);
        baseHead.put(ACCEPT, "gzip");
        baseHead.put(USER_AGENT, "Dalvik/2.1.0 (Linux; U; Android 8.1.0; Pixel Build/OPM1.171019.011)");
        baseHead.put(HOST, "app.api.ke.com");
        baseHead.put(CONNECTION, "Keep-Alive");

        baseHead.putAll(dto.getHead());
        dto.setHead(baseHead);
    }

    @Override
    protected boolean viewCheck(RequestDto requestDto) {
        return requestDto.getResponseByte().length > 0;
    }
}
