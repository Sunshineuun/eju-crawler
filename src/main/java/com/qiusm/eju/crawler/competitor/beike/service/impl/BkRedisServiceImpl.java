package com.qiusm.eju.crawler.competitor.beike.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.competitor.beike.service.IBkRedisService;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkUser;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.ThreadUtils;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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

        while (true) {
            String userStr = listOperations.index(userKey, bkUserIndex);
            bkUserIndex++;
            Long maxIndex = listOperations.size(userKey);
            if (maxIndex != null && bkUserIndex > maxIndex) {
                bkUserIndex = 0L;
            }
            if (StringUtils.isBlank(userStr)) {
                continue;
            }

            BkUser user = JSONObject.parseObject(userStr, BkUser.class);

            if (user == null || StringUtils.isBlank(user.getToken())) {
                ThreadUtils.sleep(10);
            } else {
                return user;
            }
        }
    }

    @Override
    public void pushUser(BkUser user) {
        String userKey = BK_USER_RKEY + "list";
        listOperations.leftPush(userKey, JSONObject.toJSONString(user));
    }

    @Override
    public void updateToken(BkUser user) {
        String userKey = BK_USER_RKEY + user.getPhoneNo();
        valueOperations.set(userKey, JSONObject.toJSONString(user));
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
}
