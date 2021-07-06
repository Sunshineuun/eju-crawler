package com.qiusm.eju.crawler.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author qiushengming
 */
public class ThreadUtils {

    /**
     * @param second 秒数
     */
    public static void sleep(int second) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(second));
        } catch (InterruptedException ignored) {
        }
    }
}
