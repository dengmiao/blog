package com.miao.boot.blog.base;

import com.miao.boot.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @title: BaseHandler
 * @description: 公有的handler
 * Create Retrieve Update Delete
 * 返回Publisher<Result<E>>
 * @author: dengmiao
 * @create: 2019-07-05 15:16
 **/
@Slf4j
public abstract class BaseHandler<E, ID extends Serializable> {

    /**
     * 获取server实现
     * @return
     */
    public abstract BaseReactiveService getService();

    /**
     * 获取泛型的类模板对象
     * @return
     */
    private Class<E> getClazz() {
        Class<E> clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    public Mono<ServerResponse> page(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<E> e = request.bodyToMono(getClazz());
        // 数据校验？
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getService().create(e)
                        .flatMap(et -> Mono.just(Result.ok(et))), getClazz());
    }

    /**
     * 按id查询
     * @param request
     * @return
     */
    public Mono<ServerResponse> retrieve(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getService().retrieve(id)
                        .flatMap(et -> Mono.just(Result.ok(et)))
                        .switchIfEmpty(Mono.just(Result.notFound("对象不存在"))), getClazz())
                ;
    }

    /**
     * 修改
     * @param request
     * @return
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<E> e = request.bodyToMono(getClazz());
        // 数据校验？
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getService().update(id, e)
                        .flatMap(et -> Mono.just(Result.ok(et)))
                        // 不存在
                        , getClazz())
                ;
    }

    /**
     * 删除
     * @param request
     * @return
     */
    public Mono<ServerResponse> delete(ServerRequest request) {
        String ids = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getService().delete(ids).then(Mono.just(Result.ok())), Object.class)
                ;
    }
}
