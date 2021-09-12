package com.qiusm.eju.crawler.service.bk.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.service.bk.IBkUserManagementService;
import com.qiusm.eju.crawler.service.bk.IBkUserService;
import com.qiusm.eju.crawler.utils.EmailUtil;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkUserManagementServiceImpl implements IBkUserManagementService {

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
        EntityWrapper<BkUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("phone_no", phoneNo);
        BkUser user = bkUserService.selectOne(entityWrapper);

        if (user == null) {
            user = BkUser.builder()
                    .phoneNo(phoneNo)
                    .deviceId(IdUtil.simpleUUID())
                    .build();
        }

        if (StringUtils.isNotBlank(pw)) {
            user.setPassword(pw);
        }

        return user;
    }

    @Override
    public synchronized BkUser getUser() {
        String userKey = BK_USER_RKEY + "list";
        int count = 0;
        BkUser user = null;
        while (true) {

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

            long startTime = System.currentTimeMillis();
            String userStr = listOperations.rightPop(userKey);
            bkUserIndex++;
            if (System.currentTimeMillis() - startTime > 5000) {
                log.warn("获取用户信息耗时异常！！！");
            }
            Long maxIndex = listOperations.size(userKey);
            if (maxIndex != null && bkUserIndex > maxIndex) {
                bkUserIndex = 0L;
            }

            // 长时间未获取到用户，发送邮件提醒，并退出
            if (count++ > 100) {
                // 发送邮件
                emailUtil.sendSimpleMail("583853240@qq.com", "bk用户池异常，用户池可能耗尽！");
                log.error("bk用户池耗尽！！！");
                break;
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
        if (user.getId() != null) {
            user.updateById();
        } else {
            user.insert();
        }
    }

    @Override
    public void updateUser(BkUser user) {
//        String userKey = BK_USER_RKEY + user.getPhoneNo();
//        valueOperations.set(userKey, JSONObject.toJSONString(user));
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

    @Override
    public void updateBkUser(BkUser user) {
        String userKey = BK_USER_RKEY + user.getPhoneNo();
        valueOperations.set(userKey, JSONObject.toJSONString(user));
        bkUserService.updateById(user);
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
