package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

/**
 * @title: UserReactiveRepositoryTests
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 16:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserReactiveRepositoryTests {

    @Autowired
    private UserReactiveRepository userReactiveRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void add() {
    userReactiveRepository
        .save(
            new User()
                .setUsername("admin")
                .setEmail("admin@163.com")
                .setStatus(1)
                .setRealName("超级管理员")
                .setPassword(passwordEncoder.encode("123456")))
        .map(b -> {
            System.out.println(b.getId());
            return b;
        });
    }

    @Test
    public void query() {
        Mono<User> user = userReactiveRepository.findUserByUsername("admin");
        System.out.println(user);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }
}
