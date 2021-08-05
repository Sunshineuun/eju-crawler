package com.qiusm.eju.crawler.config;

import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

//@Configuration
//    @ConditionalOnClass(Feign.class)
//    @AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig {
    // @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                .build();
    }
}