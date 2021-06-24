package com.qiusm.eju.crawler.government.base.utils;

import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.*;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL;

/**
 * 在政府源数据采集过程中，常用的工具方法 <br>
 *
 * @author qiushengming
 * @date 2021年6月23日
 */
public class CommonUtils {
    public static OkHttpUtils createHttpClient() {
        return OkHttpUtils.Builder()
                .proxyUrl(PROXY_URL)
                .connectTimeout(60000)
                .readTimeout(60000)
                .charset(GBK)
                .builderHttp();
    }

    /**
     * 创建线程池
     *
     * @param poolName      线程池名称，便于区分
     * @param nThreads      线程核心数量
     * @param keepAliveTime 线程存活时间，单位秒
     * @return {@link ThreadPoolExecutor}
     */
    public static ThreadPoolExecutor newFixedThreadPool(String poolName, int nThreads, long keepAliveTime) {
        return ThreadPoolUtils.newFixedThreadPool(poolName, nThreads, keepAliveTime);
    }

    /**
     * 关闭线程池
     *
     * @param poolExecutor 线程池
     * @return 是否成功关闭
     */
    public static boolean shutdownThreadPool(ExecutorService poolExecutor) {
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将异常的堆栈信息，转换位字符串 <br>
     *
     * @param throwable 抛出的异常
     * @return 异常的堆栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();

        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    public static void main(String[] args) {
        ExecutorService unitExecutor = CommonUtils.newFixedThreadPool("test", 10, 50L);
        // 线程池在线程执行的过程中，当队列满了之后会进行阻塞。
        for (int i = 0; i < 10; i++) {
            unitExecutor.submit(() -> {
                try {
                    System.out.printf("%s正在执行\n", Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        unitExecutor.shutdown();

        while (true) {
            if (unitExecutor.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        shutdownThreadPool(unitExecutor);

        System.out.println("执行结束");
    }
}
