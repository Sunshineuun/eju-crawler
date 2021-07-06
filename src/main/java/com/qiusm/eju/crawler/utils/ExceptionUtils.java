package com.qiusm.eju.crawler.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常工具类，主要解析异常的堆栈信息
 *
 * @author qiushengming
 */
public class ExceptionUtils {

    /**
     * 异常堆栈信息转String
     *
     * @param e 异常
     * @return String
     */
    public static String stackTraceInfoToStr(Exception e) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append(e.toString())
                .append("      ")
                .append(e.getMessage());
        // 限制长度20；将其转换为String；并通过 \n 连接起来
        String stackTraceInfo = Arrays.stream(e.getStackTrace())
                .limit(20)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
        errorMsg.append(stackTraceInfo);
        return errorMsg.toString();
    }
}
