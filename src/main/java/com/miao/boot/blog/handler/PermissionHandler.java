package com.miao.boot.blog.handler;

import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.repository.PermissionReactiveRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @title: PermissionHandler
 * @description:
 * @author: dengmiao
 * @create: 2019-07-01 16:04
 **/
@Component
public class PermissionHandler {

    private final PermissionReactiveRepository permissionReactiveRepository;

    public PermissionHandler(final PermissionReactiveRepository permissionReactiveRepository) {
        this.permissionReactiveRepository = permissionReactiveRepository;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Permission> permission = request.bodyToMono(Permission.class);
        return permission.flatMap(p -> {
            // 数据校验

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.permissionReactiveRepository.save(p), Permission.class);
        });
    }
}
