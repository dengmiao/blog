package com.miao.boot.blog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.miao.boot.blog.properties.ProjectProperties.DEFAULT_PREFIX;

/**
 * @title: ProjectProperties
 * @description: 项目信息配置
 * @author: dengmiao
 * @create: 2019-07-01 14:46
 **/
@Data
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class ProjectProperties {

    static final String DEFAULT_PREFIX = "blog.project";

    private String name;

    private String version;

    private String poweredBy;
}
