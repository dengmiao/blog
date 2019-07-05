package com.miao.boot.blog.service;

import com.miao.boot.blog.base.BaseReactiveService;
import com.miao.boot.blog.domain.User;
import reactor.core.publisher.Mono;

/**
 * @title: UserReactiveService
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 15:57
 **/
public interface UserReactiveService extends BaseReactiveService<User, String> {

    /**
     * 按用户名查找
     * @param username
     * @return
     */
    Mono<User> findByUsername(String username);
}
