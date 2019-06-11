package com.miao.boot.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

/**
 * @title: WebFluxConfig
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 10:33
 **/
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    private final ThymeleafReactiveViewResolver thymeleafReactiveViewResolver;

    public WebFluxConfig(final ThymeleafReactiveViewResolver thymeleafReactiveViewResolver) {
        this.thymeleafReactiveViewResolver = thymeleafReactiveViewResolver;
    }

    /**
     * 静态资源路径映射配置（与Spring MVC 5一样,只是引入的类不同）
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 全局跨域配置，根据各自需求定义
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(3600)
                .exposedHeaders(HttpHeaders.SET_COOKIE);
    }

    /**
     * 加入视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafReactiveViewResolver);
    }
}
