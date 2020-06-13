package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Platform;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GamesRepository {
    Set<String> findGamesTitles(String titleText, Integer page, Integer size, Boolean isProposition, Platform platform);

    List<Game> findGamesByTitleAndPlatform(Set<String> titleNames, Integer page, Integer size, Platform platform);

    Game findGameById(Integer id, Platform platform);
}
