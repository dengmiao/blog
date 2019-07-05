package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @title: PermissionRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-07-05 11:06
 **/
@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
}
