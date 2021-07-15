package com.qiusm.eju.crawler.base.parser;

import com.qiusm.eju.crawler.utils.spring.SpringContextUtils;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;

final class ParserUtils {
    private final static ValueOperations<String, Object> VALUE_OPERATIONS = SpringContextUtils.getBean("valueOperations");
    private final static ListOperations<String, Object> LIST_OPERATIONS = SpringContextUtils.getBean("listOperations");

    public static ValueOperations<String, Object> getValueOperations() {
        return VALUE_OPERATIONS;
    }

    public static ListOperations<String, Object> getListOperations() {
        return LIST_OPERATIONS;
    }
}
