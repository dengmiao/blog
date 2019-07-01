package com.miao.boot.blog.handler;

import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.repository.UserReactiveRepository;
import com.miao.boot.blog.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @title: UserHandler
 * @description: userHandler 相当于springMvc中的controller
 * 在此书写标准的restful风格的Create Retrieve Update Delete方法
 * @author: dengmiao
 * @create: 2019-06-10 15:47
 **/
@Component
public class UserHandler {

    private final UserReactiveRepository userReactiveRepository;

    public UserHandler(final UserReactiveRepository userReactiveRepository) {
        // 完成成员属性初始化 service
        this.userReactiveRepository = userReactiveRepository;
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userReactiveRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return user.flatMap(u -> {
            // 数据校验

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.userReactiveRepository.save(u), User.class);
        });
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.userReactiveRepository.findById(id)
                .flatMap(user -> this.userReactiveRepository.delete(user)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<User> user = request.bodyToMono(User.class);
        // 数据校验
        return this.userReactiveRepository.findById(id)
                .flatMap(u -> {
                    // 数据复制
                    BeanUtils.copyProperties(user, u);
                    return this.userReactiveRepository.save(u);
                })
                .map(u -> new ResponseEntity<>(Result.ok(u), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND));
    }

    public Mono<ServerResponse> retrieve(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.userReactiveRepository.findById(id)
                .map(u -> new ResponseEntity<>(Result.ok(u), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND));
    }
}
