package com.miao.boot.blog.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @title: ProjectAutoConfiguration
 * @description:
 * @author: dengmiao
 * @create: 2019-07-01 14:50
 **/
@Data
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@AutoConfigureBefore(value = WebFluxAutoConfiguration.class)
@EnableConfigurationProperties(value = {ProjectProperties.class})
public class ProjectAutoConfiguration {

    private final ProjectProperties projectProperties;

    public ProjectAutoConfiguration(final ProjectProperties projectProperties) {
        this.projectProperties = projectProperties;
    }
}
