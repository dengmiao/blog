package com.miao.boot.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @title: MainController
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 10:36
 **/
@Slf4j
@Controller
public class MainController {

    /*@GetMapping("/index")
    public String index() {
        return "/index";
    }

    @GetMapping("/login")
    public Mono<String> login() {
        return Mono.create(stringMonoSink -> stringMonoSink.success("login"));
    }*/
}
