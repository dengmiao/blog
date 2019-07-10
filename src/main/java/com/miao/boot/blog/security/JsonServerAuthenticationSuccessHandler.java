package com.miao.boot.blog.security;

import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.toolkit.ByteUtil;
import com.miao.boot.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @title: JsonServerAuthenticationSuccessHandler
 * @description: 自定义登录成功处理 返回json数据
 * @author: dengmiao
 * @create: 2019-07-09 11:12
 **/
@Slf4j
public class JsonServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler, Serializable {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info("登录成功 {}", LocalDateTime.now());
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        // 生成token并返回
        Result body = Result.ok(new HashMap<String, Object>(1){
            {
                put("token", LocalDateTime.now());
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
