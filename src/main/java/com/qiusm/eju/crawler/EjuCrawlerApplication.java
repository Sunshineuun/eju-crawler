package com.qiusm.eju.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.qiusm.eju.crawler.mapper"})
@SpringBootApplication
public class EjuCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjuCrawlerApplication.class, args);
    }

}
