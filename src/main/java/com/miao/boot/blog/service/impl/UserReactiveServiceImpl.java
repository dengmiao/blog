package com.miao.boot.blog.service.impl;

import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.domain.Role;
import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.repository.*;
import com.miao.boot.blog.service.UserReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @title: UserReactiveServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 15:58
 **/
@Slf4j
@Service
public class UserReactiveServiceImpl implements UserReactiveService {

    private final UserReactiveRepository userReactiveRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    public UserReactiveServiceImpl(final UserReactiveRepository userReactiveRepository
                                   , final RoleRepository roleRepository
                                   , final PermissionRepository permissionRepository
                                   ) {
        this.userReactiveRepository = userReactiveRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        // 需要缓存一下
        // key:role->id, value:role的map 备用
        Map<String, Role> roleMap = roleRepository.findAll().stream().collect(Collectors.toMap(Role::getId, role -> role));

        // key:permission->id, value:permission的map 备用
        Map<String, Permission> permissionMap = permissionRepository.findAll().stream()
                .collect(Collectors.toMap(Permission::getId, perm -> perm));

        return userReactiveRepository.findUserByUsername(username).flatMap(user -> {
            //
            Set<String> permissions = new HashSet<>();
            // 用户所持有的角色对象集合
            Set<Role> roleSet = user.getRoles().stream().map(id -> roleMap.get(id)).collect(Collectors.toSet());
            user.setRoleList(roleSet);
            roleSet.forEach(item -> permissions.addAll(item.getPermissions()));
            log.info("全资源: {} {}", permissionMap.keySet().size(), permissionMap);
            log.info("拥有资源id: {} {}", permissions.size(), permissions);
            // 用户持有角色对应的权限集合 并集
            List<Permission> permissionList = permissions.stream().map(id -> {
                Permission perm = permissionMap.get(id);
                if(perm == null) {
                    log.info("为空的perm: {}", id);
                }
                return perm;
            }).collect(Collectors.toList());
            log.info("拥有资源list: {}", permissionList);
            user.setPermissionList(permissionList);
            return Mono.just(user);
        })
        //.log()
        ;
    }
}
