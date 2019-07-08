package com.miao.boot.blog.base;

import cn.hutool.core.util.StrUtil;
import org.reactivestreams.Publisher;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @title: BaseReactiveService
 * @description: 封装基础crud
 * @author: dengmiao
 * @create: 2019-07-05 15:18
 **/
@FunctionalInterface
public interface BaseReactiveService<E, ID extends Serializable> {

    /**
     * getRepository
     * @return
     */
    ReactiveMongoRepository<E, ID> getRepository();

    /**
     * 单个新增entity
     * @param e
     * @return
     */
    default Mono<E> create(E e) {
        return getRepository().insert(e);
    }

    /**
     * 单个新增publisher
     * @param e
     * @return
     */
    default Mono<E> create(Mono<E> e) {
        return e.flatMap(et -> getRepository().insert(et));
    }

    /**
     * 集合新增
     * @param es
     * @return
     */
    default Flux<E> create(Iterable<E> es) {
        return getRepository().insert(es);
    }

    /**
     * 集合新增
     * @param ePublisher
     * @return
     */
    default Flux<E> create(Publisher<E> ePublisher) {
        return getRepository().insert(ePublisher);
    }

    /**
     * 按id查询
     * @param id
     * @return
     */
    default Mono<E> retrieve(ID id) {
        return getRepository().findById(id);
    }

    /**
     * 修改
     * @param id
     * @param e
     * @return
     */
    default Mono<E> update(ID id, final E e) {
        return getRepository().findById(id).flatMap(et -> {
            // 数据复制
            // 数据校验？
            BeanUtils.copyProperties(e, et);
            return getRepository().save(et);
        })// 不存在？
        ;
    }

    /**
     * 基础删除含批量删除
     * @param id
     * @return
     */
    default Mono<Void> delete(ID id) {
        String[] idArray = StrUtil.split(id.toString(), ",");
        List idList = Arrays.asList(idArray);
        return getRepository().deleteAll(idList);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    default Mono<Void> delete(Iterable<ID> ids) {
        return getRepository().deleteAll(getRepository().findAllById(ids));
    }

    /**
     * list
     * @return
     */
    default Flux<E> list() {
        return getRepository().findAll();
    }
}
