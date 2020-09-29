package com.trade4life.discs.trader.core.repository.mock;

import com.google.common.collect.Lists;
import com.trade4life.discs.trader.core.repository.UserRepository;
import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.dto.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserRepositoryMock implements UserRepository {
    private final static User TEST_USER_1 = User.builder()
        .id(1)
        .nickname("@puzzleqw")
        .name("Stanislau K.")
        .phone("375296660818")
        .bio("about me")
        .platform(Platform.PSN)
        .isBlocked(false)
        .build();

    private final static User TEST_USER_2 = User.builder()
        .id(2)
        .nickname("@somedude")
        .name("Ivanov Ivan")
        .phone("375293330818")
        .bio("duuuuuuude")
        .platform(Platform.PSN)
        .build();

    private final static User TEST_USER_3 = User.builder()
        .id(3)
        .nickname("@kappapride")
        .name("Kappa Man")
        .phone("375292220818")
        .bio("duuuuuuude")
        .platform(Platform.PSN)
        .build();
    private static int idCounter = 4;

    private static List<User> TEST_USERS = List.of(TEST_USER_1, TEST_USER_2, TEST_USER_3);
    private static Map<Integer, User> TEST_USER_BY_ID_MAP = TEST_USERS.stream()
        .collect(Collectors.toMap(User::getId, user -> user));

    @Override
    public Optional<User> findUserById(Integer id) {
        User searchResult = TEST_USER_BY_ID_MAP.get(id);
        return searchResult == null ? Optional.empty() : Optional.of(searchResult);
    }

    @Override
    public Optional<User> findUserByNickname(String nickname, Platform platform) {
        List<User> searchResult = TEST_USERS.stream()
            .filter(user -> user.getPlatform().equals(platform))
            .filter(user -> user.getNickname().equalsIgnoreCase(nickname))
            .collect(Collectors.toList());
        return searchResult.isEmpty() ? Optional.empty() : Optional.of(searchResult.get(0));
    }

    @Override
    public List<User> findUsers(Pageable pageable) {
        List<List<User>> userPages = Lists.partition(TEST_USERS, pageable.getPageSize());
        int totalPages = userPages.size();
        if (pageable.getPageNumber() > totalPages) {
            return Collections.emptyList();
        }
        return userPages.get(pageable.getPageNumber() - 1);
    }

    @Override
    public User addNewUser(User user) {
        user.setId(idCounter);
        TEST_USERS.add(user);
        TEST_USER_BY_ID_MAP.put(idCounter, user);
        idCounter++;

        return TEST_USERS.get(user.getId());
    }

    @Override
    public Optional<User> updateUser(User user) {
        if (!checkIsUserAlreadyExist(user.getId())) {
            return Optional.empty();
        }

        TEST_USER_BY_ID_MAP.put(user.getId(), user);
        TEST_USERS = new ArrayList<>(TEST_USER_BY_ID_MAP.values());
        return Optional.of(TEST_USER_BY_ID_MAP.get(user.getId()));
    }

    private boolean checkIsUserAlreadyExist(Integer id) {
        return TEST_USERS.stream().anyMatch(user -> user.getId().equals(id));
    }
}
