package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Permission;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @title: PermissionReactiveRepository
 * @description: 资源repository
 * @author: dengmiao
 * @create: 2019-07-01 15:33
 **/
@Repository
public interface PermissionReactiveRepository extends ReactiveMongoRepository<Permission, String> {
}
