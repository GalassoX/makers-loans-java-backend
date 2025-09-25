package com.makers.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(LoanHandler loanHandler, UserHandler userHandler) {
        return route(POST("/api/v1/signin"), userHandler::signIn)
                .andRoute(GET("/api/v1/loans/{userId}"), loanHandler::getUserLoans)
                .andRoute(POST("/api/v1/loans"), loanHandler::createLoan)
                .andRoute(PUT("/api/v1/loans"), loanHandler::updateLoan);
    }
}
