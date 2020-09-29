package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.dto.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findUserById(Integer id);

    User findUserByNickname(String nickname, Platform platform);

    List<User> findUsers(Pageable pageable);

    User addNewUser(User user);

    User updateUser(User user);
}
