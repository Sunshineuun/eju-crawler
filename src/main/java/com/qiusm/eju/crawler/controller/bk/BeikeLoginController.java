package com.qiusm.eju.crawler.controller.bk;

import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByPasswordV2;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PhoneVerifyCode;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.service.bk.IBeikeLoginService;
import com.qiusm.eju.crawler.service.bk.IBkUserManagementService;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.LIANJIA_CITY_ID;

/**
 * @author qiushengming
 */
@Api(tags = {"BeikeLogin", "bk用户登录"})
@Slf4j
@RestController
@RequestMapping("/bk/login")
public class BeikeLoginController {
    @Resource
    private IBkUserManagementService bkRedisService;

    @Resource
    private IBeikeLoginService beikeLoginService;

    @Resource
    private PhoneVerifyCode phoneVerifyCodeService;

    @Resource
    private LoginByVerifyCode loginByVerifyCodeService;

    @Resource
    private LoginByPasswordV2 loginByPasswordV2Service;

    @Resource
    private IBkUserManagementService redisService;

    @GetMapping("/get/picVerifyCodeByPhone")
    public void getPicVerifyCodeByPhone(
            HttpServletResponse response,
            String phoneNo,
            @RequestParam(required = false, defaultValue = "310000") String cityId) {
        try {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.write(beikeLoginService.getPicVerifyCodeByPhone(phoneNo, cityId), response.getOutputStream());
        } catch (IOException exception) {
            log.error("{}", exception.getMessage());
        }
    }

    @GetMapping("/get/phoneVerifyCodeByPhone")
    public ResponseDto getPhoneVerifyCodeByPhone(
            HttpSession session, String phoneNo, String picVerifyCode, String cityId) {
        session.setAttribute("phoneNo", phoneNo);

        BkUser user = redisService.getUserByPhoneNo(phoneNo);

        RequestDto requestDto = RequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        ResponseDto responseDto = phoneVerifyCodeService.execute(requestDto);
        return responseDto;
    }

    @GetMapping("/loginByVerifyCode")
    public ResponseDto loginByVerifyCode(
            HttpSession session, String phoneNo, String verifyCode, String picVerifyCode, String cityId) {

        BkUser user = redisService.getUserByPhoneNo(phoneNo);
        RequestDto requestDto = RequestDto.builder()
                .user(user)
                .requestParam("verify_code", verifyCode)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        ResponseDto responseDto = loginByVerifyCodeService.execute(requestDto);
        bkRedisService.pushUser(user);
        return responseDto;
    }

    @GetMapping("/loginByPasswordV2")
    public ResponseDto loginByPasswordV2(
            String phoneNo, String password, String picVerifyCode,
            @RequestParam(required = false, defaultValue = "310000") String cityId) {
        BkUser user = redisService.getUserByPhoneNo(phoneNo, password);
        RequestDto requestDto = RequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        ResponseDto responseDto = loginByPasswordV2Service.execute(requestDto);
        bkRedisService.pushUser(user);
        return responseDto;
    }

    @GetMapping("/oneClickLogin")
    public ResponseDto oneClickLogin(String phoneNo, String picVerifyCode, String cityId) {

        BkUser user = redisService.getUserByPhoneNo(phoneNo);

        if (StringUtils.isBlank(user.getPassword())) {
            throw new BusinessException(10000, "缓存密码为空，无法登录");
        }
        RequestDto requestDto = RequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        return loginByPasswordV2Service.execute(requestDto);
    }

    public BkUser getUserByPhoneNo(String phoneNo) {
        BkUser user = redisService.getUserByPhoneNo(phoneNo);
        return user;
    }

    public BkUser nowUser(String phoneNo) {
        return getUserByPhoneNo(phoneNo);
    }

}
