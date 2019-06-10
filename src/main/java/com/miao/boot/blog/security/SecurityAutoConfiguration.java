package com.miao.boot.blog.security;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @title: SecurityAutoConfiguration
 * @description: 自动配置
 * @author: dengmiao
 * @create: 2019-06-06 13:57
 **/
@Data
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@AutoConfigureBefore(value = WebFluxAutoConfiguration.class)
@EnableConfigurationProperties(value = {SecurityProperties.class})
public class SecurityAutoConfiguration {

    private final SecurityProperties securityProperties;

    public SecurityAutoConfiguration(final SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
