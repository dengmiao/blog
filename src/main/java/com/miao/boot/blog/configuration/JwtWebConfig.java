/*
package com.miao.boot.blog.configuration;

import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.constant.CommonConstant;
import com.miao.boot.blog.vo.Result;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

import static com.miao.boot.blog.constant.CommonConstant.Security;

*/
/**
 * @title: JwtWebConfig
 * @description:
 * @author: dengmiao
 * @create: 2019-07-11 16:05
 **//*

@Slf4j
@Configuration
public class JwtWebConfig implements WebFilter {

    @Value("${blog.security.login-url}")
    private String loginUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request=  exchange.getRequest();
        log.info(request.getPath().value());
        String path = request.getPath().value();
        if(path.contains(loginUrl) || path.endsWith(".html") || path.endsWith(".js") || path.endsWith(".css") || path.endsWith(".ico")){
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        String authorization=request.getHeaders().getFirst(Security.HEADER);
        if(authorization == null || ! authorization.startsWith(Security.TOKEN_SPLIT)){
            return this.setErrorResponse(response,"未携带token");
        }
        String token = authorization.substring(7);
        try {
            exchange.getAttributes().put("token", Jwts.parser()
                    .setSigningKey(CommonConstant.Security.JWT_SIGN_KEY)
                    .parseClaimsJws(token.replace(CommonConstant.Security.TOKEN_SPLIT, ""))
                    .getBody());
        }catch(Exception e) {
            return this.setErrorResponse(response,e.getMessage());
        }
        return  chain.filter(exchange);
    }

    protected Mono<Void> setErrorResponse(ServerHttpResponse response, String message){
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String result = JSONUtil.formatJsonStr(JSONUtil.toJsonStr(Result.error(message)));
        try {
            return response.writeWith(Mono.just(response.bufferFactory().wrap(result.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }
}
*/
