//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.qiusm.eju.crawler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreadsUtils {
    private static final Logger log = LoggerFactory.getLogger(ThreadsUtils.class);
    private ExecutorService executorService;
    private final String poolName;
    private final AtomicInteger poolIndex = new AtomicInteger(0);

    public ThreadsUtils() {
        this.poolName = "onion-head-";
    }

    public ThreadsUtils(String poolName) {
        this.poolName = poolName + "-";
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
                t.setName(poolName + poolIndex.getAndIncrement());
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
            log.error("awaitTermination exception.{}", var4.getMessage());
            this.executorService.shutdownNow();
        }

    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute) {
        return this.executeFutures(list, execute, false);
    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, boolean isShutdown) {
        return this.executeFutures(list, execute, isShutdown, null);
    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, Integer threadNum) {
        return this.executeFutures(list, execute, false, threadNum);
    }

    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, boolean isShutdown, Integer threadNum) {
        if (execute == null) {
            return null;
        } else {
            List<R> rs = null;

            try {
                List<CompletableFuture<R>> executeFutures = list.parallelStream()
                        .filter(Objects::nonNull)
                        .map((e) -> CompletableFuture.supplyAsync(() -> {
                            try {
                                return execute.apply(e);
                            } catch (Exception var3) {
                                log.error("executeFutures error:{}", var3.getMessage(), var3);
                                return null;
                            }
                        }, this.getExecutorService(threadNum)))
                        .collect(Collectors.toList());

                rs = executeFutures.stream().map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

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
}
