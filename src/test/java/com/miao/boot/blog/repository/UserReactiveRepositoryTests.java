package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: UserReactiveRepositoryTests
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 16:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserReactiveRepositoryTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserReactiveRepository userReactiveRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleReactiveRepository roleReactiveRepository;

    @Test
    public void create() {
        final List<String> roles = roleReactiveRepository.findAll().collectList().block().stream().map(permission -> permission.getId()).collect(Collectors.toList());
        User user = new User()
                .setUsername("admin")
                .setEmail("admin@163.com")
                .setStatus(1)
                .setRealName("超级管理员")
                .setPassword(passwordEncoder.encode("123456"))
                .setRoles(roles)
                ;

        webTestClient.post().uri("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.username").isEqualTo("admin");
    }

    @Test
    public void update() {
        /*Mono<Long> count = permissionReactiveRepository.findAll().count();
        System.out.println("-----------------" + count.block());*/
        final List<String> permissions = roleReactiveRepository.findAll().collectList().block().stream().map(permission -> permission.getId()).collect(Collectors.toList());
        User user = new User().setRoles(permissions);
        webTestClient.put().uri("/user/5d1ad981cf743e1f447730b9")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.permissions").isNotEmpty()
        ;
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
