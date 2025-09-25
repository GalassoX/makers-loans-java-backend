package com.makers.api.config;

import com.makers.api.dto.response.Response;
import com.makers.model.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<Response<?>>> handleBusinessException(BusinessException e) {
        return Mono.just(ResponseEntity.badRequest()
                .body(Response.builder()
                    .code(e.getCode())
                    .message(e.getMessage())
                    .data(null)
                    .build()));
    }

//    @ExceptionHandler(Exception.class)
//    public Mono<ResponseEntity<Response<?>>> handleException(Exception e) {
//        return Mono.just(ResponseEntity.badRequest()
//                .body(Response.builder()
//                        .code(1)
//                        .message(e.getMessage())
//                        .data(null)
//                        .build()));
//    }
}
