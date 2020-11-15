package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.repository.UserRepository;
import com.trade4life.discs.trader.core.service.UserService;
import com.trade4life.discs.trader.core.domain.User;
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
    public User findUserById(String id) {
        return userRepository.findUserById(id)
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
    }

    @Override
    public User findUserByTelegramId(String telegramId) {
        return userRepository.findUserByTelegramId(telegramId)
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
    }

    @Override
    public User findUserByNickname(String nickname) {
        return userRepository.findUserByNickname(nickname)
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
    }

    @Override
    public List<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public User addNewUser(User user) {
        if (user.getId() != null) {
            return updateUser(user);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        userRepository.findUserByTelegramId(user.getTelegramId())
            .orElseThrow(() -> new CoreException(USER_NOT_FOUND, NOT_FOUND));
        return userRepository.save(user);
    }
}
