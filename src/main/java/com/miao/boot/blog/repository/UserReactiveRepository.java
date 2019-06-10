package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @title: UserReactiveRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 15:49
 **/
@Repository
public interface UserReactiveRepository extends ReactiveMongoRepository<User, String> {

    /**
     * 按用户名查找
     * @param username
     * @return
     */
    Mono<User> findUserByUsername(String username);
}
