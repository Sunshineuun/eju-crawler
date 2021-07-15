package com.qiusm.eju.crawler.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qiusm.eju.crawler.base.entity.Parser;
import com.qiusm.eju.crawler.base.entity.TaskInstance;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务池具体任务对象
 *
 * @author qiushengming
 */
@Data
public class TaskInstanceRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    String host;
    String url;
    /**
     * 字符集
     */
    String charset;
    /**
     * url的请求睡眠时间，单位 秒
     */
    Integer sleepTime = 0;
    /**
     * 解析器的ID
     */
    Long parserId;
    String code;
    /**
     * 0:url 1:数据库
     */
    String sourceType;
    /**
     * 失败重试次数
     */
    Integer tryNum = 3;
    /**
     * 是否需要登录cookie 0：false  1：true
     */
    Boolean isLogin;
    /**
     * 解析器之间的共享数据传递
     */
    Map<String, Object> data = new HashMap<>();
    /**
     * url任务链接请求的参数
     */
    Map<String, Object> params = new HashMap<>();
    /**
     * url任务请求头
     */
    Map<String, String> heads = new HashMap<>();
    /**
     * url的请求类型 get，post-json,post-form
     */
    RequestMethodEnum method = RequestMethodEnum.GET;

    /**
     * 任务实例，表示当前请求源自哪个任务实例
     */
    @JsonIgnore
    TaskInstance taskInstance;

    /**
     * 当前请求实例中获取到新的请求实例
     */
    @JsonIgnore
    List<TaskInstanceRequest> newRequest;

    /**
     * 检查当前请求是否与当前解析器的ID一致；
     * 当前请求的解析器ID是否等于解析器的ID，或者当前请求的解析器CODE是否等于解析器的code
     */
    public boolean checkParser(Parser parser) {
        return (this.getParserId() != null && parser.getId().compareTo(this.getParserId()) == 0)
                || (StringUtils.isNotBlank(this.getCode()) && StringUtils.equals(this.getCode(), parser.getCode()));
    }
}
