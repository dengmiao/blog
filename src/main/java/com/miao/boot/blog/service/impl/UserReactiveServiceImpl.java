package com.miao.boot.blog.service.impl;

import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.repository.UserReactiveRepository;
import com.miao.boot.blog.service.UserReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @title: UserReactiveServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 15:58
 **/
@Service
public class UserReactiveServiceImpl implements UserReactiveService {

    private final UserReactiveRepository userReactiveRepository;

    public UserReactiveServiceImpl(final UserReactiveRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userReactiveRepository.findUserByUsername(username);
    }
}
