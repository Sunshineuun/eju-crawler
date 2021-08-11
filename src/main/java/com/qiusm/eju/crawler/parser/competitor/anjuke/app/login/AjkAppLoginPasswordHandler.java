package com.qiusm.eju.crawler.parser.competitor.anjuke.app.login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.base.HttpBase;
import com.qiusm.eju.crawler.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.USER_NAME;

/**
 * AJK 密码登陆 <br>
 * 加密后的代码，硬编码在代码中，所以注册的时候，请统一使用【qwer12345】<br>
 *
 * @author qiusAjgming
 */
@Slf4j
@Service
public class AjkAppLoginPasswordHandler extends HttpBase {
    private static final String URL_TEMPLATE = "https://passport.58.com/ajk/login/app/dologin";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        requestDto.setUrl(URL_TEMPLATE);
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(USER_NAME)) {
            throw new BusinessException(10000, "参数缺失：" + USER_NAME);
        }
        requestParam.put("passportCallBackType", "2");
        requestParam.put("password", "Kpp5tamB89kOUGLxuBbwg8uI9c3Ut8AdlUE5znzivPVSNK--ICssjwT222E9ngdV54W-TIYkMXuem2LDmM40me6KjE7xgLSd07CBa2noQO-sJiMdUHKQaB-0rFNaATxmWzDtYY9Nsj-F6VIZUE_ETkuBDBUNM8Djj0u8gkHC6woB");
        requestParam.put("vptype", "RSA2");
        requestParam.put("rsakeyversion", "1");
        requestParam.put("source", "ajk-anjuke-android");
        requestParam.put("isremember", "true");
        requestParam.put("validcodetype", "200");
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray ticket = JSONUtils.getJsonArrayByKey(mainJson, "data.ticket");
        if (ticket == null) {
            log.error("登陆失败！{}", requestDto);
        }
        StringBuilder cookie = new StringBuilder();
        ticket.forEach(o -> {
            JSONObject var = (JSONObject) o;
            cookie.append(var.getString("name")).append("=").append(var.getString("value"));
        });
        responseDto.getResult().put("cookie", cookie.toString());
    }

    @Override
    protected String getSourceType() {
        return SourceTypeEnum.AJK_APP.getCode();
    }

    @Override
    protected void httpGet(RequestDto requestDto) {
        super.httpGetA(requestDto);
    }
}
