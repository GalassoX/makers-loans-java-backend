package com.makers.model.user.gateways;

import com.makers.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> getUserByEmail(String email);
    Mono<User> getUserById(Integer id);
}
