package com.qiusm.eju.crawler.competitor.beike.service;

import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkUser;

/**
 * @author qiushengming
 */
public interface IBeikeLoginService {

    /**
     * 获取验证码图片字节流
     *
     * @param phoneNo 手机号码
     * @param cityId  城市6位编码
     * @return
     */
    byte[] getPicVerifyCodeByPhone(String phoneNo, String cityId);

    BkUser loginByPasswordV2(
            String phoneNo, String password, String picVerifyCode, String cityId);
}
