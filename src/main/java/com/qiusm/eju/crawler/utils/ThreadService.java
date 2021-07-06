package com.qiusm.eju.crawler.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author qiushengming
 */
@Slf4j
public class ThreadService {
    private ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return this.getExecutorService(null);
    }

    public synchronized ExecutorService getExecutorService(Integer num) {
        if (this.executorService == null || this.executorService.isShutdown()) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            num = num == null ? availableProcessors * 2 : num;
            log.info("active thread num: {}", num);
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
            log.error("awaitTermination exception.{}", var4.getMessage());
            this.executorService.shutdownNow();
        }

    }

    /**
     * 执行过程中会对进入的任务进行过滤处理，过滤为null的数据； <br>
     * 返回结果将会对结果列表进行过滤处理，过滤为null的数据。 <br>
     *
     * @param list       需要被处理的任务列表
     * @param execute    对任务处理的逻辑代码
     * @param isShutdown 执行完后是否关闭线程池
     * @param threadNum  线程数量
     * @param <T>        任务的类型
     * @param <R>        execute的返回结果
     * @return 返回execute执行的结果
     */
    public <T, R> List<R> executeFutures(List<T> list, Function<T, R> execute, boolean isShutdown, Integer threadNum) {
        if (execute == null) {
            return null;
        } else {
            List<R> rs = null;

            try {
                // 1. 过滤掉为空的内容 2. 对任务进行包装。包装成【CompletableFuture】执行。
                List<CompletableFuture<R>> executeFutures = list.parallelStream()
                        .filter(Objects::nonNull)
                        .map((e) -> CompletableFuture.supplyAsync(() -> {
                            // 任务执行失败返回null，否则返回执行结果
                            try {
                                return execute.apply(e);
                            } catch (Exception var3) {
                                log.error("executeFutures error:{}", var3.getMessage(), var3);
                                return null;
                            }
                        }, this.getExecutorService(threadNum)))
                        .collect(Collectors.toList());

                rs = executeFutures.stream()
                        .map(CompletableFuture::join)
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

    public static void main(String[] args) {
        ThreadService threadUtils = new ThreadService();
        List<Object> result = threadUtils.executeFutures(IntStream.range(1, 16).boxed().collect(Collectors.toList()), (e) -> {
            return UUID.randomUUID().toString();
        }, true, 5);
        System.out.println(result);
    }
}
