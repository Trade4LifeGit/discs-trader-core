package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.service.dto.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findUserById(String id);

    User findUserByTelegramId(String telegramId);

    User findUserByNickname(String nickname);

    List<User> findUsers(Pageable pageable);

    User addNewUser(User user);

    User updateUser(User user);
}
