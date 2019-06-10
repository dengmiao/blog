package com.miao.boot.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * 启动类
 * @author dengmiao
 */
@Slf4j
@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableWebFlux
public class BlogApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("启动完成。。");
	}
}
