package com.miao.boot.blog.configuration;

import com.miao.boot.blog.security.SecurityProperties;
import com.miao.boot.blog.security.WebReactiveUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import java.net.URI;

/**
 * @title: SecurityConfig
 * @description: security安全配置类
 * 由@EnableWebSecurity变为@EnableWebFluxSecurity webflux不再继承WebSecurityConfigurerAdapter
 * @author: dengmiao
 * @create: 2019-06-06 11:27
 **/
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * spring security自带的密码加密工具类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return new WebReactiveUserDetailsServiceImpl();
    }

    /**
     * 基本配置
     * @param http
     * @return
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                // 授权配置
                .authorizeExchange()
                // 跳过验证 直接访问
                .pathMatchers(securityProperties.getAnonResourcesUrl()).permitAll()
                // 需要角色才能访问
                .pathMatchers("/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .and()
                // 表单登录验证
                .formLogin()
                // 自定义登录界面
                .loginPage(securityProperties.getLoginUrl())
                // 登录成果处理
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
                // 登录失败处理
                //.authenticationFailureHandler(null)
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler(securityProperties.getLogoutUrl()))
                .and()
                .build()
        ;
    }

    public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }
}
