package com.makers.jpa.user;

import com.makers.jpa.user.entity.UserEntity;
import com.makers.jpa.user.helper.AdapterOperations;
import com.makers.model.user.User;
import com.makers.model.user.gateways.UserRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserJPARepositoryAdapter extends AdapterOperations<User, UserEntity, Integer, UserJPARepository>
        implements UserRepository {

    public UserJPARepositoryAdapter(UserJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Mono<User> getUserByEmail(String email) {
        return Mono.justOrEmpty(repository.findByEmail(email))
                .map(userEntity -> mapper.map(userEntity, User.class));
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(userEntity -> mapper.map(userEntity, User.class));
    }
}
