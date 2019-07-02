package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Permission;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @title: PermissionReactiveRepository
 * @description: 资源repository
 * @author: dengmiao
 * @create: 2019-07-01 15:33
 **/
@Repository
public interface PermissionReactiveRepository extends ReactiveMongoRepository<Permission, String> {

    /**
     * 按pid查询
     * @param pid
     * @return
     */
    Flux<Permission> findPermissionsByPid(String pid);
}
