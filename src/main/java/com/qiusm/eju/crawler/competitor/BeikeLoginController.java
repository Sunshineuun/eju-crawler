package com.qiusm.eju.crawler.competitor;

import com.qiusm.eju.crawler.competitor.beike.service.IBkRedisService;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByPasswordV2;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PhoneVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PicVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkUser;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.LIANJIA_CITY_ID;

/**
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/bk/login")
public class BeikeLoginController {
    @Resource
    private IBkRedisService bkRedisService;

    @Resource
    private PicVerifyCode picVerifyCodeService;

    @Resource
    private PhoneVerifyCode phoneVerifyCodeService;

    @Resource
    private LoginByVerifyCode loginByVerifyCodeService;

    @Resource
    private LoginByPasswordV2 loginByPasswordV2Service;

    @Resource
    private IBkRedisService redisService;

    @GetMapping("/get/picVerifyCodeByPhone")
    public void getPicVerifyCodeByPhone(HttpServletResponse response,
                                        HttpServletRequest request,
                                        String phoneNo, String cityId) {
        request.getSession().setAttribute("phoneNo", phoneNo);

        BkUser user = redisService.getUserByPhoneNo(phoneNo);
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(user)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        BkResponseDto responseDto = picVerifyCodeService.execute(requestDto);
        try {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.write(responseDto.getResultByte(), response.getOutputStream());
        } catch (IOException exception) {
            log.error("{}", exception.getMessage());
        }
    }

    @GetMapping("/get/phoneVerifyCodeByPhone")
    public BkResponseDto getPhoneVerifyCodeByPhone(
            HttpSession session, String phoneNo, String picVerifyCode, String cityId) {
        session.setAttribute("phoneNo", phoneNo);

        BkUser user = redisService.getUserByPhoneNo(phoneNo);

        BkRequestDto requestDto = BkRequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        BkResponseDto responseDto = phoneVerifyCodeService.execute(requestDto);
        return responseDto;
    }

    @GetMapping("/loginByVerifyCode")
    public BkResponseDto loginByVerifyCode(
            HttpSession session, String phoneNo, String verifyCode, String picVerifyCode, String cityId) {

        BkUser user = redisService.getUserByPhoneNo(phoneNo);
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(user)
                .requestParam("verify_code", verifyCode)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        BkResponseDto responseDto = loginByVerifyCodeService.execute(requestDto);
        bkRedisService.pushUser(user);
        return responseDto;
    }

    @GetMapping("/loginByPasswordV2")
    public BkResponseDto loginByPasswordV2(
            String phoneNo, String password, String picVerifyCode, String cityId) {
        BkUser user = redisService.getUserByPhoneNo(phoneNo, password);
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        BkResponseDto responseDto = loginByPasswordV2Service.execute(requestDto);
        bkRedisService.pushUser(user);
        return responseDto;
    }

    @GetMapping("/oneClickLogin")
    public BkResponseDto oneClickLogin(String phoneNo, String picVerifyCode, String cityId) {

        BkUser user = redisService.getUserByPhoneNo(phoneNo);

        if (StringUtils.isBlank(user.getPassword())) {
            throw new BusinessException("缓存密码为空，无法登录");
        }
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        return loginByPasswordV2Service.execute(requestDto);
    }


    @GetMapping("/userBack")
    public void userBack() {
        bkRedisService.userBack();
    }

    public BkUser getUserByPhoneNo(String phoneNo) {
        BkUser user = redisService.getUserByPhoneNo(phoneNo);
        return user;
    }

    public BkUser nowUser(String phoneNo) {
        return getUserByPhoneNo(phoneNo);
    }

}
