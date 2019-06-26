package com.miao.boot.blog.configuration;

import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.handler.UserHandler;
import com.miao.boot.blog.handler.ViewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
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
    public RouterFunction<ServerResponse> viewRoutes(final ViewHandler view) {
        return RouterFunctions
                .route(RequestPredicates.GET("/login"),
                        /*view::login*/
                        req -> ServerResponse
                                .ok()
                                .contentType(MediaType.TEXT_HTML)
                                .render("login",
                                        req.exchange().getAttributes())
                )
                .andRoute(RequestPredicates.GET("/logout"),
                        req -> ServerResponse.ok().render("index")
                )
                .andRoute(RequestPredicates.GET("/welcome"),
                        req -> ServerResponse.ok().render("welcome")
                )
                /*.filter((req, resHandler) ->
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

                )*/
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
                )
        ;
    }

    @Bean
    public RouterFunction<ServerResponse> webFluxRoutesRegister(final UserHandler userHandler) {
        return RouterFunctions.nest(
                // 相当于controller的 路由前缀 @RequestMapping("/user")
                RequestPredicates.path("/user"),
                // 相当于@RequestMapping
                RouterFunctions.route(RequestPredicates.GET("/list"), userHandler::list)
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::create)
                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::delete)
        );
    }
}
