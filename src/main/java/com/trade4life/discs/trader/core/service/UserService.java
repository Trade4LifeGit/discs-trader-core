package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.domain.User;
import com.trade4life.discs.trader.core.service.dto.UserResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User findUserById(String id);

    User findUserByTelegramId(String telegramId);

    User findUserByNickname(String nickname);

    UserResponse findUsers(Pageable pageable);

    User addNewUser(User user);

    User updateUser(User user);
}
