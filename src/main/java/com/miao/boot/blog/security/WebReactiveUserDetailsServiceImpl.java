package com.miao.boot.blog.security;

import com.miao.boot.blog.service.UserReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * @title: WebReactiveUserDetailsServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 15:53
 **/
@Slf4j
public class WebReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private UserReactiveService userReactiveService;

    @Override
    public Mono<UserDetails> findByUsername(final String username) {
        return userReactiveService.findByUsername(username)
                .flatMap(u -> Mono.just(new SecurityUserDetails(u)));
    }
}
