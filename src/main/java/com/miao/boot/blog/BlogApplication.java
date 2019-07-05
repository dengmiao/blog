package com.miao.boot.blog;

import com.miao.boot.blog.properties.ProjectProperties;
import com.miao.boot.blog.toolkit.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.springframework.security.config.Elements.HTTP;

/**
 * 启动类
 * @author dengmiao
 */
@Slf4j
@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableMongoRepositories
@EnableWebFlux
public class BlogApplication implements ApplicationRunner {

	public static void main(String[] args) throws UnknownHostException {
		//获取开始时间
		long start = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(BlogApplication.class);
		Environment env = app.run(args).getEnvironment();
		String protocol = HTTP;
		//获取结束时间
		long end = System.currentTimeMillis();
		ProjectProperties projectProperties = ((ProjectProperties) SpringContextUtil.getBean(ProjectProperties.class));
		// 应用上下文 没有上下文根
		String context = env.getProperty("server.servlet.context-path");
		context = context != null ? context : "";
		// 端口号
		String port = env.getProperty("server.port");
		log.info("\n----------------------------------------------------------\n\t"
						+ "名称:\t'{}' is running! Access URLs:\n\t"
						+ "本地:\t {}://localhost:{}{}\n\t"
						+ "外部:\t {}://{}:{}{}\n\t"
						+ "环境:\t {}\n\t"
						+ "版本:\t {}\n\t"
						+ "用时:\t {}\n"
						+ "----------------------------------------------------------",
				projectProperties.getName(),
				protocol, port, context,
				protocol, InetAddress.getLocalHost().getHostAddress(), port, context,
				env.getActiveProfiles(),
				projectProperties.getVersion(),
				(end - start) + "ms");
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("启动完成。。");
	}
}
