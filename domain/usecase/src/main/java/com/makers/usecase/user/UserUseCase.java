package com.makers.usecase.user;

import com.makers.model.exceptions.BusinessException;
import com.makers.model.user.User;
import com.makers.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<Integer> signIn(String email, String password) {
        return userRepository.getUserByEmail(email)
                .switchIfEmpty(Mono.error(new BusinessException(1001, "Contraseña incorrecta")))
                .filter(user -> checkPassword(user.getPassword(), password))
                .map(User::getId)
                .switchIfEmpty(Mono.error(new BusinessException(1000, "Contraseña incorrecta")));
    }

    private Boolean checkPassword(String userPassword, String password) {
        return Objects.equals(userPassword, password);
    }
}
