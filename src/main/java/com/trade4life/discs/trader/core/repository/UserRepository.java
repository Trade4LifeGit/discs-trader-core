package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.dto.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findUserById(Integer id);

    Optional<User> findUserByNickname(String nickname, Platform platform);

    List<User> findUsers(Pageable pageable);

    User addNewUser(User user);

    Optional<User> updateUser(User user);
}
