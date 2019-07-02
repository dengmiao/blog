package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @title: RoleReactiveRepository
 * @description: 角色repository
 * @author: dengmiao
 * @create: 2019-07-02 14:53
 **/
@Repository
public interface RoleReactiveRepository extends ReactiveMongoRepository<Role, String> {
}
