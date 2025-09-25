package com.makers.api;

import com.makers.api.dto.request.SignIn;
import com.makers.api.dto.response.SignInResponse;
import com.makers.api.util.ResponseBuilder;
import com.makers.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserUseCase userUseCase;

    public Mono<ServerResponse> signIn(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SignIn.class)
                .flatMap(signIn -> userUseCase.signIn(signIn.email(), signIn.password()))
                .flatMap(userId -> ResponseBuilder.buildSuccessResponse(SignInResponse.builder()
                                .userId(userId)
                                .build(), 200));
    }
}
