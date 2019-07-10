package com.miao.boot.blog.security;

import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

import static com.miao.boot.blog.constant.CommonConstant.LoginError;

/**
 * @title: JsonServerAuthenticationFailureHandler
 * @description: 自定义登录失败处理 返回json数据
 * @author: dengmiao
 * @create: 2019-07-09 11:59
 **/
@Slf4j
public class JsonServerAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        if (exception instanceof UsernameNotFoundException) {
            return writeErrorMessage(response, LoginError.USER_NOT_EXISTS);
        } else if (exception instanceof BadCredentialsException) {
            return writeErrorMessage(response, LoginError.USERNAME_PASSWORD_ERROR);
        } else if (exception instanceof LockedException) {
            return writeErrorMessage(response, LoginError.USER_LOCKED);
        }
        return writeErrorMessage(response, LoginError.SYSTEM_ERROR);
    }

    private Mono<Void> writeErrorMessage(ServerHttpResponse response, String errorMsg) {
        String result = JSONUtil.formatJsonStr(JSONUtil.toJsonStr(Result.error(errorMsg)));
        DataBuffer buffer = null;
        try {
            buffer = response.bufferFactory().wrap(result.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response.writeWith(Mono.just(buffer));
    }
}
