package com.makers.api.util;

import com.makers.api.dto.response.Response;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@UtilityClass
public class ResponseBuilder {
    public static <T> Mono<ServerResponse> buildSuccessResponse(T data, Integer status) {
        return ServerResponse
                .status(status)
                .bodyValue(Response.builder()
                        .code(0)
                        .message("")
                        .data(data)
                        .build());
    }

    public static <T> Mono<ServerResponse> buildResponse(T data, Integer code, String message, Integer status) {
        return ServerResponse.status(status).bodyValue(Response.builder()
                        .code(code)
                        .message(message)
                        .data(data)
                .build());
    }
}
