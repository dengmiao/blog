package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: RoleReactiveRepositoryTest
 * @description:
 * @author: dengmiao
 * @create: 2019-07-02 14:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleReactiveRepositoryTest {

    @Autowired
    private RoleReactiveRepository roleReactiveRepository;

    @Autowired
    private PermissionReactiveRepository permissionReactiveRepository;

    @Test
    public void create() {
        final List<String> permissions = permissionReactiveRepository.findAll().collectList().block().stream().map(permission -> permission.getId()).collect(Collectors.toList());
        roleReactiveRepository.save(new Role()
                .setCode("ADMIN")
                .setName("超级管理员")
                .setPermissions(permissions)
        ).block(); // 阻塞 执行
    }
}
