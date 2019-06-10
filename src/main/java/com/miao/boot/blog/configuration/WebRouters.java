package com.miao.boot.blog.configuration;

import com.miao.boot.blog.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @title: WebRouters
 * @description: 路由配置
 * @author: dengmiao
 * @create: 2019-06-10 13:35
 **/
@Component
public class WebRouters {

    @Bean
    public RouterFunction<?> iconResources() {
        return RouterFunctions
                .resources("/favicon.**", new ClassPathResource("static/favicon.ico"));
    }

    @Bean
    public RouterFunction<?> viewRoutes() {
        return RouterFunctions
                .route(RequestPredicates.GET("/login"),
                        req -> ServerResponse
                                .ok()
                                .render("login",
                                        req.exchange().getAttributes())
                )
                /*.andRoute(RequestPredicates.GET("/bye"),
                        req -> ServerResponse.ok().render("bye")
                )
                .filter((req, resHandler) ->
                        req.exchange()
                                .getAttributeOrDefault(
                                        CsrfToken.class.getName(),
                                        Mono.empty().ofType(CsrfToken.class)
                                )
                                .flatMap(csrfToken -> {
                                    req.exchange()
                                            .getAttributes()
                                            .put(csrfToken.getParameterName(), csrfToken);
                                    return resHandler.handle(req);
                                })

                )
                .andRoute(RequestPredicates.GET("/"),
                        req -> req.principal()
                                .ofType(Authentication.class)
                                .flatMap(auth -> {
                                    User user = User.class.cast(auth.getPrincipal());
                                    req.exchange()
                                            .getAttributes()
                                            .putAll(Collections.singletonMap("user", user));
                                    return ServerResponse.ok().render("index",
                                            req.exchange().getAttributes());
                                })
                )*/
                ;
    }
}
