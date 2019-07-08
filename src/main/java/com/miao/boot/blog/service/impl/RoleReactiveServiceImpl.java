package com.miao.boot.blog.service.impl;

import com.miao.boot.blog.domain.Role;
import com.miao.boot.blog.repository.RoleReactiveRepository;
import com.miao.boot.blog.service.RoleReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

/**
 * @title: RoleReactiveServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-07-08 17:19
 **/
@Slf4j
@Service
public class RoleReactiveServiceImpl implements RoleReactiveService {

    private final RoleReactiveRepository roleReactiveRepository;

    public RoleReactiveServiceImpl(
            final RoleReactiveRepository roleReactiveRepository
    ) {
        this.roleReactiveRepository = roleReactiveRepository;
    }

    @Override
    public ReactiveMongoRepository<Role, String> getRepository() {
        return this.roleReactiveRepository;
    }
}
