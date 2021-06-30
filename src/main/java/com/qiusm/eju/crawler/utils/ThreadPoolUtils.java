package com.qiusm.eju.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 *
 * @author qiushengming
 */
@Slf4j
public class ThreadPoolUtils {
    /**
     * 创建线程池
     *
     * @param poolName      线程池名称，便于区分
     * @param nThreads      线程核心数量
     * @param keepAliveTime 线程存活时间，单位秒
     * @return {@link ThreadPoolExecutor}
     */
    public static ThreadPoolExecutor newFixedThreadPool(String poolName, int nThreads, long keepAliveTime) {
        ThreadFactory basicThreadFactory = new BasicThreadFactory
                .Builder()
                .namingPattern(poolName + "-%s ")
                .build();

        return new ThreadPoolExecutor(nThreads,
                nThreads * 2,
                keepAliveTime,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                basicThreadFactory,
                (r, exe) -> {
                    if (!exe.isShutdown()) {
                        try {
                            exe.getQueue().put(r);
                        } catch (InterruptedException ignored) {
                        }
                    }
                });
    }
}
