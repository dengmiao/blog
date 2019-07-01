package com.miao.boot.blog.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @title: CommonHandler
 * @description:
 * @author: dengmiao
 * @create: 2019-07-01 16:41
 **/
@Component
public class CommonHandler {

    public Mono<ServerResponse> logout(ServerRequest request) {
        return null;
    }
}
