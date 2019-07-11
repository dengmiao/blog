package com.miao.boot.blog.security;

import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.vo.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.miao.boot.blog.constant.CommonConstant.Security;

/**
 * @title: JsonServerAuthenticationSuccessHandler
 * @description: 自定义登录成功处理 返回json数据
 * @author: dengmiao
 * @create: 2019-07-09 11:12
 **/
@Slf4j
public class JsonServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler, Serializable {

    @Value("${blog.security.token.expireTime}")
    private Integer tokenExpireTime;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info("登录成功 {}", LocalDateTime.now());
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        // /**text/plain;charset=UTF-8*/返回json
        response.getHeaders().add("Content-Type", "application/json");

        User user = User.class.cast(authentication.getPrincipal());
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails)authentication.getPrincipal()).getAuthorities();
        List<String> list = authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        // 生成token并返回
        final String token = Security.TOKEN_SPLIT + Jwts.builder()
                // 主题 放入用户名
                .setSubject(user.getUsername())
                //自定义属性 放入用户拥有请求权限
                .claim(Security.AUTHORITIES, list)
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, Security.JWT_SIGN_KEY)
                .compact()
                ;
        Result body = Result.ok(new HashMap<String, Object>(1){
            {
                put("token", token);
            }
        });
        String result = JSONUtil.formatJsonStr(JSONUtil.toJsonStr(body));
        DataBuffer buffer = null;
        try {
            buffer = response.bufferFactory().wrap(result.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.writeWith(Mono.just(buffer));
    }
}
