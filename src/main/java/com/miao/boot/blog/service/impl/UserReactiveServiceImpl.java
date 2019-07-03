package com.miao.boot.blog.service.impl;

import com.miao.boot.blog.domain.Role;
import com.miao.boot.blog.domain.User;
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

    public UserReactiveServiceImpl(final UserReactiveRepository userReactiveRepository
                                   ,final RoleReactiveRepository roleReactiveRepository
                                   ) {
        this.userReactiveRepository = userReactiveRepository;
        this.roleReactiveRepository = roleReactiveRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        // id为key的map 备用
        Map<String, Role> roleMap = new HashMap<>(16);
        // 需要缓存一下
        final Flux<Role> roleFlux =  roleReactiveRepository.findAll();
        roleFlux.map(role -> {
            roleMap.put(role.getId(), role);
            return role;
        }).subscribe();// 不知道合适不 不让消费 哈哈 不理解reactor

        return userReactiveRepository.findUserByUsername(username).flatMap(user -> {
            // 用户所持有的角色对象集合
            Set<Role> roleSet1 = user.getRoles().stream().map(id -> roleMap.get(id)).collect(Collectors.toSet());
            user.setRoleList(roleSet1);
            /*List<String> roles = user.getRoles();
            Set<Role> roleSet = new HashSet<>();
            roleFlux.reduce(roleSet, (rt, item) -> {
                if(roles != null && roles.size() != 0 && roles.indexOf(item.getId()) != -1) {
                    rt.add(item);
                }
                return rt;
            }).map(r -> {
                user.setRoleList(r);
                log.info("角色: {}", r.size());
                return r;
            }).subscribe();*/
            return Mono.just(user);
        })
        //.log()
        ;
    }
}
