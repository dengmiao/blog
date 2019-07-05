package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @title: RoleRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-07-05 11:22
 **/
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
}
