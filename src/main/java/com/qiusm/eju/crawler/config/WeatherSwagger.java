package com.qiusm.eju.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;

/**
 * @author qiushengming
 */
@Configuration
@EnableSwagger2
public class WeatherSwagger {
    @Bean
    public Docket createSwagger() {
        //文档类型（swagger2）
        return new Docket(DocumentationType.SWAGGER_2)
                // //设置包含在json ResourceListing响应中的api元信息
                .apiInfo(apiInfo())
                // //启动用于api选择的构建器
                .select()
                //扫描接口的包
                .apis(RequestHandlerSelectors.basePackage("com.qiusm.eju.crawler"))
                //路径过滤器（扫描所有路径）
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("eju-crawler API文档")
                .description("eju-crawler")
                .termsOfServiceUrl("https://xxx.xyz")
                .version("1.0")
                .build();
    }
}