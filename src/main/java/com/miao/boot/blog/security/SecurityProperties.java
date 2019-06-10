package com.miao.boot.blog.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.miao.boot.blog.security.SecurityProperties.DEFAULT_PREFIX;

/**
 * @title: SecurityProperties
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 13:51
 **/
@Data
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class SecurityProperties {

    static final String DEFAULT_PREFIX = "blog.security";

    /**
     * 登录 URL
     */
    private String loginUrl;

    /**
     * 登出 URL
     */
    private String logoutUrl;

    /**
     * 主页 URL
     */
    private String indexUrl;

    /**
     * 免认证静态资源路径
     */
    private String[] anonResourcesUrl;

    /**
     * 记住我超时时间
     */
    private int rememberMeTimeout;
}
