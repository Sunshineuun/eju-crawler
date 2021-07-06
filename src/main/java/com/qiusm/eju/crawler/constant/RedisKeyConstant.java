package com.qiusm.eju.crawler.constant;

/**
 * @author qiushengming
 */
public class RedisKeyConstant {
    /**
     * 爬虫的根
     */
    public static final String CRAWLER_KEY = "house:crawler:";
    /**
     * 任务列表的key
     */
    public static final String TASK_POOL_KEY = CRAWLER_KEY + "taskpool:";
    /**
     * 等待的任务列表
     */
    public static final String WAIT_TASK_KEY = CRAWLER_KEY + "waittask";
    /**
     * 计数
     */
    public static final String TOTAL_KEY = CRAWLER_KEY + "total:";
}
