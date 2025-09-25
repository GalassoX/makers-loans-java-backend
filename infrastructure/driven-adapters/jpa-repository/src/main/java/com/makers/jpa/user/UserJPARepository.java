package com.makers.jpa.user;

import com.makers.jpa.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserJPARepository extends CrudRepository<UserEntity, Integer>, QueryByExampleExecutor<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
