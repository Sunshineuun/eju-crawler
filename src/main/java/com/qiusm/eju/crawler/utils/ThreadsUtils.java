//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.qiusm.eju.crawler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreadsUtils {
    private static final Logger log = LoggerFactory.getLogger(ThreadsUtils.class);
    private ExecutorService executorService;
    private String poolName;

    public ThreadsUtils() {
    }

    public ThreadsUtils(String poolName) {
        this.poolName = poolName;
    }

    public ExecutorService getExecutorService() {
        return this.getExecutorService(null);
    }

    public synchronized ExecutorService getExecutorService(Integer num) {
        if (this.executorService == null || this.executorService.isShutdown()) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            num = num == null ? availableProcessors * 2 : num;
            log.info("active thread num:" + num);
            this.executorService = Executors.newFixedThreadPool(num, (r) -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            });
        }

        return this.executorService;
    }

    public void shutdown() {
        if (this.executorService != null && !this.executorService.isShutdown()) {
            this.executorService.shutdown();
        }

    }

    public void shutdown(long timeout) {
        try {
            if (this.executorService != null && !this.executorService.isShutdown()) {
                this.executorService.shutdown();
                if (!this.executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
                    this.executorService.shutdownNow();
                }
            }
        } catch (InterruptedException var4) {
            log.error("awaitTermination exception", var4.getMessage());
            this.executorService.shutdownNow();
        }

    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute) {
        return this.executeFutures(list, execute, false);
    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, boolean isShutdown) {
        return this.executeFutures(list, execute, isShutdown, (Integer)null);
    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, Integer threadNum) {
        return this.executeFutures(list, execute, false, threadNum);
    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, boolean isShutdown, Integer threadNum) {
        if (execute == null) {
            return null;
        } else {
            List rs = null;

            try {
                List<CompletableFuture<R>> executeFutures = (List)list.parallelStream().filter((e) -> {
                    return e != null;
                }).map((e) -> {
                    return CompletableFuture.supplyAsync(() -> {
                        try {
                            return execute.apply(e);
                        } catch (Exception var3) {
                            log.error("executeFutures error:{}", var3.getMessage(), var3);
                            return null;
                        }
                    }, this.getExecutorService(threadNum));
                }).collect(Collectors.toList());
                rs = (List)executeFutures.stream().map(CompletableFuture::join).filter((e) -> {
                    return e != null;
                }).collect(Collectors.toList());
            } catch (RejectedExecutionException var10) {
                log.error("Shutting down");
            } finally {
                if (isShutdown) {
                    this.shutdown();
                }

            }

            return rs;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();

        for(int i = 0; i < 100; ++i) {
            list.add(i);
        }

        List<Integer> rs = (new ThreadsUtils()).executeFutures(list, (e) -> {
            System.out.println(e);
            return e;
        });
        System.out.println(rs);
    }
}
