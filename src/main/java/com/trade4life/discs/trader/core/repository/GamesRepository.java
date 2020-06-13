package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Platform;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GamesRepository {
    Set<String> findGamesTitles(String titleText, Boolean isProposition, Platform platform, Pageable pageable);

    List<Game> findGamesByTitleAndPlatform(Set<String> titleNames, Platform platform, Pageable pageable);

    Game findGameById(Integer id, Platform platform);
}
