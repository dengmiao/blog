package com.miao.boot.blog.repository;

import com.miao.boot.blog.domain.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @title: PermissionReactiveRepositoryTests
 * @description:
 * @author: dengmiao
 * @create: 2019-07-01 15:43
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionReactiveRepositoryTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PermissionReactiveRepository permissionReactiveRepository;

    @Test
    public void insert() {
        Permission permission = new Permission()
                .setPid("0")
                .setName("test")
                .setIcon("&#xe6b8;")
                .setType("0")
                .setRouting("");
        /*permissionReactiveRepository.save(
                permission
        ).map(b -> {
            System.out.println(b.getId());
            return b;
        });*/

        webTestClient.post().uri("/permission/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(permission), Permission.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("test");
    }

}
