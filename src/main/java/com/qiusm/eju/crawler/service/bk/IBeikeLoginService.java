package com.qiusm.eju.crawler.service.bk;

import com.qiusm.eju.crawler.entity.bk.BkUser;

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
