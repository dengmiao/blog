package com.miao.boot.blog.service.impl;

import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.domain.Role;
import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.repository.PermissionReactiveRepository;
import com.miao.boot.blog.repository.RoleReactiveRepository;
import com.miao.boot.blog.repository.UserReactiveRepository;
import com.miao.boot.blog.service.UserReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    private final RoleReactiveRepository roleReactiveRepository;

    private final PermissionReactiveRepository permissionReactiveRepository;

    public UserReactiveServiceImpl(final UserReactiveRepository userReactiveRepository
                                   , final RoleReactiveRepository roleReactiveRepository
                                   , final PermissionReactiveRepository permissionReactiveRepository
                                   ) {
        this.userReactiveRepository = userReactiveRepository;
        this.roleReactiveRepository = roleReactiveRepository;
        this.permissionReactiveRepository = permissionReactiveRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        // key:role->id, value:role的map 备用
        Map<String, Role> roleMap = new HashMap<>(16);
        // key:permission->id, value:permission的map 备用
        Map<String, Permission> permissionMap = new HashMap<>(16);
        // 需要缓存一下
        final Flux<Role> roleFlux =  roleReactiveRepository.findAll();
        roleFlux.map(role -> {
            roleMap.put(role.getId(), role);
            return role;
        }).subscribe();// 不知道合适不 不让消费 哈哈 不理解reactor

        permissionReactiveRepository.findAll().map(permission -> {
            permissionMap.put(permission.getId(), permission);
            return permission;
        }).subscribe();

        return userReactiveRepository.findUserByUsername(username).flatMap(user -> {
            //
            Set<String> permissions = new HashSet<>();
            // 用户所持有的角色对象集合
            Set<Role> roleSet = user.getRoles().stream().map(id -> roleMap.get(id)).collect(Collectors.toSet());
            user.setRoleList(roleSet);
            roleSet.forEach(item -> permissions.addAll(item.getPermissions()));
            // 用户持有角色对应的权限集合 并集
            List<Permission> permissionList = permissions.stream().map(id -> permissionMap.get(id)).collect(Collectors.toList());
            user.setPermissionList(permissionList);
            return Mono.just(user);
        })
        //.log()
        ;
    }
}
