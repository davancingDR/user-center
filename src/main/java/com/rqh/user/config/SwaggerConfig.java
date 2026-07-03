package com.rqh.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * API 文档配置
 */
@Configuration
public class SwaggerConfig {

    /**
     * 文档基本信息
     */
    @Bean
    public OpenAPI userCenterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("用户中心API文档")
                .description("用户中心API文档")
                .version("v1.0.0")
                .contact(new Contact().name("rqh")));
    }

    /**
     * 分组扫描
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("用户中心")
                .packagesToScan("com.rqh.user.controller")
                .build();
    }
}
