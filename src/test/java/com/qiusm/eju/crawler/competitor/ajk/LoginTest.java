package com.qiusm.eju.crawler.competitor.ajk;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.login.AjkAppLoginPasswordHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

@SpringBootTest
public class LoginTest {

    @Resource
    AjkAppLoginPasswordHandler ajkLoginPassword;

    /**
     * qwer12345
     */
    @Test
    void loginTest() {
        Map<String, String> params = new HashMap<>(4);
        params.put(USER_NAME, "13341702682");
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.POST_FORM)
                .head("user-agent", "PSDK-A/2.4.0.1 (Android;9;Xiaomi;MI 6;native) %E5%AE%89%E5%B1%85%E5%AE%A2/15.13")
                .build();
        ajkLoginPassword.execute(requestDto);
    }
}
