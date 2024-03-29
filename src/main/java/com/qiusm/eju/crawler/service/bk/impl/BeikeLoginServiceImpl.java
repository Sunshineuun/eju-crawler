package com.qiusm.eju.crawler.service.bk.impl;

import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByPasswordV2;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PicVerifyCode;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.service.bk.IBeikeLoginService;
import com.qiusm.eju.crawler.service.bk.IBkUserManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.LIANJIA_CITY_ID;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BeikeLoginServiceImpl
        implements IBeikeLoginService {
    @Resource
    private IBkUserManagementService redisService;

    @Resource
    private PicVerifyCode picVerifyCodeService;

    @Resource
    private LoginByPasswordV2 loginByPasswordV2Service;

    @Override
    public byte[] getPicVerifyCodeByPhone(String phoneNo, String cityId) {

        BkUser user = redisService.getUserByPhoneNo(phoneNo);
        RequestDto requestDto = RequestDto.builder()
                .user(user)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        ResponseDto responseDto = picVerifyCodeService.execute(requestDto);
        return responseDto.getResultByte();
    }

    @Override
    public BkUser loginByPasswordV2(
            String phoneNo, String password, String picVerifyCode, String cityId) {
        BkUser user = redisService.getUserByPhoneNo(phoneNo, password);
        RequestDto requestDto = RequestDto.builder()
                .user(user)
                .requestParam("pic_verify_code", picVerifyCode)
                .head(LIANJIA_CITY_ID, cityId)
                .build();
        ResponseDto responseDto = loginByPasswordV2Service.execute(requestDto);
        if (!responseDto.getSuccess()) {
            user.setState(91);
        }else{
            user.setState(1);
        }
        redisService.pushUser(user);
        log.info("{}", user);
        return user;
    }
}
