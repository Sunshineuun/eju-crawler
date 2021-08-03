package com.qiusm.eju.crawler.competitor.beike;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByPasswordV2;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PhoneVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PicVerifyCode;
import com.qiusm.eju.crawler.service.bk.IBkRedisService;
import com.qiusm.eju.crawler.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.LIANJIA_CITY_ID;

@SpringBootTest
public class LoginTest {

    @Autowired
    private IBkRedisService bkRedisService;

    @Autowired
    private LoginByPasswordV2 loginByPasswordV2;

    @Test
    void loginByPasswordV2() {
        Map<String, String> params = new HashMap<>(1);
        params.put("pic_verify_code", "1082");
        RequestDto requestDto = RequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = loginByPasswordV2.execute(requestDto);
        System.out.printf("loginByVerifyCode:%s\n", responseDto);
    }

    void loginByVerifyCode() {
        IHttpSearch httpSearch = new LoginByVerifyCode();
        Map<String, String> params = new HashMap<>(1);
        params.put("pic_verify_code", "3023");
        params.put("verify_code", "739734");
        RequestDto requestDto = RequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("loginByVerifyCode:%s\n", responseDto);
    }

    void imgVerification() {
        IHttpSearch httpSearch = new PicVerifyCode();
        Map<String, String> params = new HashMap<>(1);
        RequestDto requestDto = RequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        FileUtils.printFile(responseDto.getResultByte(), "source", "1.png", false);
        System.out.printf("imgVerification:%s\n", responseDto);
    }

    /**
     * 发送验证码;
     * {"request_id":"64083d97-e980-4648-96cf-fe8eecfe79ce","uniqid":"010ACA160D1DDD9B9E7A0194597357AB","errno":0,"error":"操作成功","data":{},"cost":192}
     */
    void phoneVerification() {
        IHttpSearch httpSearch = new PhoneVerifyCode();
        Map<String, String> params = new HashMap<>();
        params.put("pic_verify_code", "2070");

        RequestDto requestDto = RequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("phoneVerification:%s\n", requestDto);
        System.out.printf("phoneVerification:%s\n", responseDto);
    }
}
