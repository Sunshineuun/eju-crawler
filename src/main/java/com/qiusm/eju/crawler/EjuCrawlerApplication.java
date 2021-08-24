package com.qiusm.eju.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan(basePackages = {"com.qiusm.eju.crawler.mapper"})
@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class EjuCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjuCrawlerApplication.class, args);
    }

}
