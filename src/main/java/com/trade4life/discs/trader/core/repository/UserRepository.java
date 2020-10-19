package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserById(String id);

    @Query(value = "{'id': {$ne : null}}")
    List<User> findAllUsers(Pageable pageable);

    @Query(value = "{'nickname': {$eq : ?0}}")
    Optional<User> findUserByNickname(String nickname);

    @Query(value = "{'telegramId': {$eq : ?0}}")
    Optional<User> findUserByTelegramId(String telegramId);
}
