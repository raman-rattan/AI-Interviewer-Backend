package com.aiInterviewer.Gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myroute(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/api/v1/**")
                        .uri("lb://userAuthentication"))
                .route(p -> p.path("/api/v2/**")
                        .uri("lb://interviewSession"))
                .build();
    }
}
