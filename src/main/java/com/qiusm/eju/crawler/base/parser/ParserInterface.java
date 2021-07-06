package com.qiusm.eju.crawler.base.parser;


import com.qiusm.eju.crawler.base.vo.TaskInstanceRequest;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Predicate;

/**
 * 解析器接口
 *
 * @author qiushengming
 */
public interface ParserInterface {

    void execute(String htmlStr, TaskInstanceRequest request, OkHttpUtils okHttpUtils);

    /**
     * 自己的编码
     *
     * @return 编码
     */
    String getCode();

    /**
     * 父节点的编码
     *
     * @return 父编码
     */
    String getParentCode();

    /**
     * 网页请求正常，服务返回页面与解析器的预期验证
     *
     * @return false 不重试，true 重试
     */
    default Predicate<String> viewTry() {
        return (html) -> StringUtils.isBlank(html)
                || html.startsWith("ejuResponseCode")
                || html.startsWith("ResponseError")
                || html.startsWith("ResponseCode");
    }

    /**
     * 构建请求头
     *
     * @param taskInstanceRequest 请求实例
     * @return 请求头
     */
    default Map<String, String> buildingHeader(TaskInstanceRequest taskInstanceRequest) {
        return taskInstanceRequest.getHeads();
    }
}
