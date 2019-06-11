package com.miao.boot.blog.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @title: ViewHandler
 * @description:
 * @author: dengmiao
 * @create: 2019-06-11 08:51
 **/
@Component
public class ViewHandler {

    @Value("classpath:/templates/login.html") Resource index;

    public Mono<ServerResponse> login(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(index);
    }
}
