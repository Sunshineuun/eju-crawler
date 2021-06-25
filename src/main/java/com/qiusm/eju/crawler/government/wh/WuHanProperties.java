package com.qiusm.eju.crawler.government.wh;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 武汉爬虫的各种配置项
 *
 * @author qiushengming
 */
@Data
@ConfigurationProperties(
        prefix = "eju.crawler.government.wuhan"
)
public class WuHanProperties {
    /**
     * 线程池最大核心线程数量
     */
    private int maxThreadCoreCount = 8;
    /**
     * html解析失败的存储路径
     */
    private String errorHtmlPath = "D:\\Temp\\CRAWLER\\WU_HAN";
    /**
     * 图片下载存储根路径
     */
    private String picturePath = "D:\\Temp\\CRAWLER\\WU_HAN\\IMG\\";
}
