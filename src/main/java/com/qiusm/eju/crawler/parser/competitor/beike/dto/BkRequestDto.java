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
public class BkRequestDto {
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求的字符串集
     */
    private String charset;
    /**
     * 请求返回的结果
     */
    private String responseStr;
    /**
     * 请求使用的参数
     */
    private Map<String, String> requestParam;
    /**
     * 请求头
     */
    private Map<String, String> head;

    private BkRequestDto(Builder builder) {
        this.url = builder.url;
        this.charset = builder.charset;
        this.requestParam = builder.requestParam;
        this.head = builder.head;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        String url;
        String charset = UTF8;
        Map<String, String> requestParam = new HashMap<>();
        Map<String, String> head = new HashMap<>();

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

        public BkRequestDto build() {
            return new BkRequestDto(this);
        }


    }

}
