package com.miao.boot.blog.service.impl;

import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.repository.PermissionReactiveRepository;
import com.miao.boot.blog.service.PermissionReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

/**
 * @title: PermissionReactiveServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-07-08 17:14
 **/
@Slf4j
@Service
public class PermissionReactiveServiceImpl implements PermissionReactiveService {

    private final PermissionReactiveRepository permissionReactiveRepository;

    public PermissionReactiveServiceImpl(
            final PermissionReactiveRepository permissionReactiveRepository
    ) {
        this.permissionReactiveRepository = permissionReactiveRepository;
    }

    @Override
    public ReactiveMongoRepository<Permission, String> getRepository() {
        return this.permissionReactiveRepository;
    }
}
