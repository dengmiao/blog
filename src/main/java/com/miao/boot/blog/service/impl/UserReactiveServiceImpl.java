package com.miao.boot.blog.service.impl;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    public UserReactiveServiceImpl(final UserReactiveRepository userReactiveRepository,
                                   final RoleReactiveRepository roleReactiveRepository,
                                   final PermissionReactiveRepository permissionReactiveRepository
                                   ) {
        this.userReactiveRepository = userReactiveRepository;
        this.roleReactiveRepository = roleReactiveRepository;
        this.permissionReactiveRepository = permissionReactiveRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        // 获取所有角色并转换为id为key的map 备用 可缓存
        Map<String, Role> roleMap = new HashMap<>();
        final Flux<Role> roleFlux =  roleReactiveRepository.findAll();
        roleFlux.filter(role -> role.getId().equals("1"));
        roleFlux.doOnComplete(() -> System.out.println("asdasdasdasd"));
        /*Map<String, Role> roleMap = roleReactiveRepository.findAll().collectList().block()
                .stream().collect(Collectors.toMap(role -> role.getId(), role -> role));*/
        Mono<User> userMono = userReactiveRepository.findUserByUsername(username);
        userMono.flatMap(user -> {
            // 用户所持有的角色对象集合
            Set<Role> roleSet = user.getRoles().stream().map(id -> roleMap.get(id)).collect(Collectors.toSet());
            user.setRoleList(roleSet);
            log.info("角色: {}", roleSet.size());
            return Mono.just(user);
        });
        return userMono;
    }
}
