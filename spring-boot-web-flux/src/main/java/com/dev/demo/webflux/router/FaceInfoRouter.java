package com.dev.demo.webflux.router;

import com.dev.demo.webflux.hanlder.FaceInfoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <p>Title: FaceInfoRouter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-19 11:00
 */
@Configuration
public class FaceInfoRouter {
    @Bean
    public RouterFunction<ServerResponse> routeCity(FaceInfoHandler cityHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/faceInfo").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),cityHandler::getFaceInfo);
    }
}
