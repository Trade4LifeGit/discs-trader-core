package com.trade4life.discs.trader.core.repository.mock;

import com.google.common.collect.Lists;
import com.trade4life.discs.trader.core.repository.GamesRepository;
import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Platform;

import java.util.*;
import java.util.stream.Collectors;

public class PsnGamesRepositoryMock implements GamesRepository {
    private final static String TEST_TITLE_1 = "The Witcher 3: Wild Hunt";
    private final static String TEST_TITLE_2 = "The Witcher 3: Wild Hunt — Hearts of Stone";
    private final static String TEST_TITLE_3 = "The Witcher 3: Wild Hunt — Blood and Wine";
    private final static String TEST_TITLE_4 = "Dark Souls: Remastered";
    private final static String TEST_TITLE_5 = "LittleBigPlanet 3";
    private final static Map<Platform, Set<String>> TEST_TITLES_BY_PLATFORM = new HashMap<>();
    private final static Set<String> TEST_TITLES = Set.of(TEST_TITLE_1, TEST_TITLE_2, TEST_TITLE_3, TEST_TITLE_4, TEST_TITLE_5);
    static {
        TEST_TITLES_BY_PLATFORM.put(Platform.PSN, TEST_TITLES);
        TEST_TITLES_BY_PLATFORM.put(Platform.ESHOP, Collections.emptySet());
    }

    private final static Game TEST_GAME_1 = Game.builder()
        .id(1)
        .title(TEST_TITLE_1)
        .description("description")
        .image("https://bit.ly/2C1oaH3")
        .build();

    private final static Game TEST_GAME_2 = Game.builder()
        .id(2)
        .title(TEST_TITLE_2)
        .description("description")
        .image("https://bit.ly/2B8t8S8")
        .build();

    private final static Game TEST_GAME_3 = Game.builder()
        .id(3)
        .title(TEST_TITLE_3)
        .description("description")
        .image("https://bit.ly/3cWoinV")
        .build();

    private final static Game TEST_GAME_4 = Game.builder()
        .id(4)
        .title(TEST_TITLE_4)
        .description("description")
        .image("https://bit.ly/3hmL1gp")
        .build();

    private final static Game TEST_GAME_5 = Game.builder()
        .id(5)
        .title(TEST_TITLE_5)
        .description("description")
        .image("https://bit.ly/3fmMW2Y")
        .build();

    private final static List<Game> TEST_GAMES = List.of(TEST_GAME_1, TEST_GAME_2, TEST_GAME_3, TEST_GAME_4, TEST_GAME_5);
    private final static Map<Platform, List<Game>> TEST_GAMES_BY_PLATFORM = new HashMap<>();
    static {
        TEST_GAMES_BY_PLATFORM.put(Platform.PSN, TEST_GAMES);
        TEST_GAMES_BY_PLATFORM.put(Platform.ESHOP, Collections.emptyList());
    }

    public Set<String> findGamesTitles(String titleText, Integer page, Integer size, Boolean isProposition, Platform platform) {
        Set<String> titlesByPlatform = TEST_TITLES_BY_PLATFORM.get(platform);

        List<String> searchResult = titlesByPlatform.stream()
            .filter(title -> isProposition ? title.contains(titleText) : title.equalsIgnoreCase(titleText))
            .collect(Collectors.toList());

        if (searchResult.isEmpty()) {
            return Collections.emptySet();
        }

        List<List<String>> pages = Lists.partition(searchResult, size);

        return page > pages.size() ? Collections.emptySet() : new HashSet<>(pages.get(page - 1));
    }

    public List<Game> findGamesByTitleAndPlatform(Set<String> titleNames, Integer page, Integer size, Platform platform) {
        List<Game> gamesByPlatform = TEST_GAMES_BY_PLATFORM.get(platform);
        List<Game> searchResult = gamesByPlatform.stream()
            .filter(game -> titleNames.isEmpty() || titleNames.contains(game.getTitle()))
            .collect(Collectors.toList());
        List<List<Game>> pages = Lists.partition(searchResult, size);
        int totalPages = pages.size();
        if (page > totalPages) {
            return Collections.emptyList();
        }
        return pages.get(page - 1);
    }

    public Game findGameById(Integer id, Platform platform) {
        List<Game> gamesByPlatform = TEST_GAMES_BY_PLATFORM.get(platform);
        List<Game> searchResult = gamesByPlatform.stream()
            .filter(game -> game.getId().equals(id))
            .collect(Collectors.toList());
        return searchResult.isEmpty() ? null : searchResult.get(0);
    }
}
