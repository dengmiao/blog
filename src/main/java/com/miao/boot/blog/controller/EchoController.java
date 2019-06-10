package com.miao.boot.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: EchoController
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 10:36
 **/
@RestController
@RequestMapping("/echo")
public class EchoController {

    @RequestMapping
    public String echo() {
        return "hello";
    }
}
