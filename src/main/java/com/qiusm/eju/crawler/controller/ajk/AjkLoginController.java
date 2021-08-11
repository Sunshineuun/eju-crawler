package com.qiusm.eju.crawler.controller.ajk;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.login.AjkAppLoginPasswordHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.USER_NAME;

/**
 * AJK登陆
 *
 * @author qiushengming
 */
@RestController
@RequestMapping("/ajk/login")
public class AjkLoginController {

    @Resource
    private AjkAppLoginPasswordHandler loginPasswordHandler;

    @GetMapping("loginPassword")
    public ResponseEntity<?> loginPassword(@RequestParam(defaultValue = "13341702682") String userName) {
        Map<String, String> params = new HashMap<>(4);
        params.put(USER_NAME, userName);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.POST_FORM)
                .head("user-agent", "PSDK-A/2.4.0.1 (Android;9;Xiaomi;MI 6;native) %E5%AE%89%E5%B1%85%E5%AE%A2/15.13")
                .build();
        ResponseDto responseDto = loginPasswordHandler.execute(requestDto);
        return ResponseEntity.ok(responseDto.getResult());
    }
}
