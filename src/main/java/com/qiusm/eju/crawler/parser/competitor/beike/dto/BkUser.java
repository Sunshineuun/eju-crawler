package com.qiusm.eju.crawler.parser.competitor.beike.dto;

import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;

/**
 * @author qiushengming
 */
@Data
public class BkUser {
    /**
     * 手机号码
     */
    private String phoneNo;
    /**
     * 设备号
     */
    private String deviceId;
    /**
     * token
     */
    private String token;

    /**
     * 密码
     */
    private String password;

    public BkUser() {

    }

    private BkUser(Builder builder) {
        this.phoneNo = builder.phoneNo;
        this.password = builder.password;
        this.deviceId = builder.deviceId;
    }

    public static BkUser.Builder builder() {
        return new BkUser.Builder();
    }

    public static final class Builder {
        String phoneNo;
        String deviceId;
        String password;

        public BkUser.Builder phoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public BkUser.Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public BkUser.Builder password(String password) {
            this.password = password;
            return this;
        }

        public BkUser build() {
            return new BkUser(this);
        }
    }
}
