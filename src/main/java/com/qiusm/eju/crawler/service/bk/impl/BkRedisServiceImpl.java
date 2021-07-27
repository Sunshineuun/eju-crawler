package com.qiusm.eju.crawler.service.bk.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.service.bk.IBkRedisService;
import com.qiusm.eju.crawler.service.bk.IBkUserService;
import com.qiusm.eju.crawler.utils.EmailUtil;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.ThreadUtils;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author qiushengming
 */
@Service
public class BkRedisServiceImpl implements IBkRedisService {

    public static final String BK_USER_RKEY = "hook:bk:user:";

    /**
     * 用户索引
     */
    private Long bkUserIndex = 0L;

    @Resource
    private ValueOperations<String, String> valueOperations;

    @Resource
    private ListOperations<String, String> listOperations;

    @Resource
    private EmailUtil emailUtil;

    @Resource
    private IBkUserService bkUserService;

    @Override
    public BkUser getUserByPhoneNo(String phoneNo) {
        return getUserByPhoneNo(phoneNo, null);
    }

    @Override
    public BkUser getUserByPhoneNo(String phoneNo, String pw) {
        String userKey = BK_USER_RKEY + phoneNo;
        String userStr = valueOperations.get(userKey);
        BkUser user;

        if (StringUtils.isBlank(userStr)) {
            user = BkUser.builder()
                    .phoneNo(phoneNo)
                    .deviceId(IdUtil.simpleUUID())
                    .build();
            valueOperations.set(userKey, JSONObject.toJSONString(user));
        } else {
            user = JSONObject.parseObject(userStr, BkUser.class);
        }

        if (StringUtils.isNotBlank(pw)) {
            user.setPassword(pw);
            valueOperations.set(userKey, JSONObject.toJSONString(user));
        }

        return user;
    }

    @Override
    public BkUser getUser() {
        String userKey = BK_USER_RKEY + "list";
        int count = 0;
        BkUser user = null;
        while (true) {
            String userStr = listOperations.rightPop(userKey);
            bkUserIndex++;
            Long maxIndex = listOperations.size(userKey);
            if (maxIndex != null && bkUserIndex > maxIndex) {
                bkUserIndex = 0L;
            }

            // 长时间未获取到用户，发送邮件提醒，并退出
            if (count++ > 100) {
                // 发送邮件
                emailUtil.sendSimpleMail("583853240@qq.com", "bk用户池异常，用户池可能耗尽！");
                break;
            }


            // 当队列为空的时候，尝试从数据库中加载
            Long keySize = listOperations.size(userKey);
            if (keySize != null && keySize <= 0) {
                EntityWrapper<BkUser> wrapper = new EntityWrapper<>();
                wrapper.eq("state", 1);
                List<BkUser> users = bkUserService.selectList(wrapper);
                for (BkUser bkUser : users) {
                    listOperations.leftPush(userKey, JSONObject.toJSONString(bkUser));
                }
            }

            if (StringUtils.isBlank(userStr)) {
                continue;
            }

            user = JSONObject.parseObject(userStr, BkUser.class);
            // 获取到有效用户退出循环
            if (user != null && StringUtils.isNotBlank(user.getToken())) {
                break;
            }

            ThreadUtil.sleep(10_000);
        }
        return user;
    }

    @Override
    public void pushUser(BkUser user) {
        String userKey = BK_USER_RKEY + "list";
        listOperations.leftPush(userKey, JSONObject.toJSONString(user));
        user.insert();
    }

    @Override
    public void updateToken(BkUser user) {
        String userKey = BK_USER_RKEY + user.getPhoneNo();
        valueOperations.set(userKey, JSONObject.toJSONString(user));
        bkUserService.updateById(user);
    }

    @Override
    public void userBack() {
        String userKey = BK_USER_RKEY + "list";
        String userKeyBack = BK_USER_RKEY + "list_back";
        Long size = listOperations.size(userKey);
        if (size != null) {
            for (long i = 0; i < size; i++) {
                String var = listOperations.index(userKey, i);
                if (StringUtils.isNotBlank(var)) {
                    listOperations.leftPush(userKeyBack, var);
                }
            }
        }

    }

    @Override
    public List<? extends BkUser> getUserList(int startIndex) {
        return getUserList(startIndex, 50);
    }

    private List<? extends BkUser> getUserList(int startIndex, int limit) {
        String userKey = BK_USER_RKEY + "list";
        List<BkUser> bkUsers = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            String userStr = listOperations.index(userKey, startIndex + i);
            bkUsers.add(JSONObject.parseObject(userStr, BkUser.class));
        }

        return bkUsers;
    }
}
