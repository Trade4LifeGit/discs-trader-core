package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.repository.UserRepository;
import com.trade4life.discs.trader.core.service.UserService;
import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.dto.User;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id)
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
    }

    @Override
    public User findUserByNickname(String nickname, Platform platform) {
        return userRepository.findUserByNickname(nickname, platform)
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
    }

    @Override
    public List<User> findUsers(Pageable pageable) {
        return userRepository.findUsers(pageable);
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.addNewUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user)
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
    }
}
