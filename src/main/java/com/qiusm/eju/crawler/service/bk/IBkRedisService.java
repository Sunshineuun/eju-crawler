package com.qiusm.eju.crawler.service.bk;


import com.qiusm.eju.crawler.entity.bk.BkUser;

import java.util.List;

/**
 * 贝壳redis支持
 *
 * @author qiushengming
 */
public interface IBkRedisService {

    /**
     * 通过手机号码获取用户对象
     *
     * @param phoneNo 手机号码
     * @return BkUser
     */
    BkUser getUserByPhoneNo(String phoneNo);

    /**
     * 通过手机号码获取用户对象，并更新密码
     *
     * @param phoneNo 手机号码
     * @param pw      密码
     * @return BkUser
     */
    BkUser getUserByPhoneNo(String phoneNo, String pw);

    /**
     * 从用户队列中获取用户
     *
     * @return 用户
     */
    BkUser getUser();

    /**
     * 将用户推入用户队列中
     *
     * @param user 用户
     */
    void pushUser(BkUser user);

    /**
     * 更新token
     *
     * @param user BkUser
     */
    void updateToken(BkUser user);

    /**
     * 备份用户
     */
    void userBack();

    /**
     * 获取用户列表
     *
     * @param startIndex 开始的位置
     * @return 用户列表
     */
    List<? extends BkUser> getUserList(int startIndex);
}
