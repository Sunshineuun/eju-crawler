package com.qiusm.eju.crawler.dto;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;

/**
 * @author qiushengming
 */
@Data
public class RequestDto {
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求的字符串集
     */
    private String charset;
    /**
     * 请求方式
     */
    private RequestMethodEnum requestMethod;
    /**
     * 判断是否需要登录
     */
    private Boolean isLoad;
    /**
     * 请求返回的结果
     */
    private String responseStr;
    /**
     * 用于传输图片、文件流
     */
    private byte[] responseByte;
    /**
     * 请求使用的参数
     */
    private Map<String, String> requestParam;
    /**
     * 请求头
     */
    private Map<String, String> head;

    private Map<String, Object> data;
    /**
     * 用户信息
     */
    private BkUser user;

    private RequestDto(Builder builder) {
        this.url = builder.url;
        this.charset = builder.charset;
        this.requestParam = builder.requestParam;
        this.head = builder.head;
        this.data = builder.data;
        this.user = builder.user;
        this.isLoad = builder.isLoad;
        this.requestMethod = builder.requestMethod;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        RequestMethodEnum requestMethod = RequestMethodEnum.GET;
        String url;
        String charset = UTF8;
        Map<String, String> requestParam = new HashMap<>();
        Map<String, String> head = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        BkUser user;
        Boolean isLoad = false;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder charset(String charset) {
            if (StringUtils.isNotBlank(charset)) {
                this.charset = charset;
            }
            return this;
        }

        public Builder requestParam(String key, String value) {
            this.requestParam.put(key, value);
            return this;
        }

        public Builder requestParam(Map<String, String> requestParam) {
            this.requestParam.putAll(requestParam);
            return this;
        }

        public Builder head(String key, String value) {
            this.head.put(key, value);
            return this;
        }

        public Builder head(Map<String, String> head) {
            this.head.putAll(head);
            return this;
        }

        public Builder user(BkUser user) {
            this.user = user;
            return this;
        }

        public RequestDto build() {
            return new RequestDto(this);
        }


        public Builder data(Map<String, Object> data) {
            this.data.putAll(data);
            return this;
        }

        public Builder data(JSONObject data) {
            this.data.putAll(data);
            return this;
        }

        public Builder isLoad(boolean isLoad) {
            this.isLoad = isLoad;
            return this;
        }

        public Builder requestMethod(RequestMethodEnum requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }
    }

}
