package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Platform;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GamesRepository {
    Optional<String> findGameTitle(String titleText, Platform platform);

    Set<String> findGameTitlePropositions(String titleText, Platform platform, Integer propositionSize);

    List<Game> findGamesByTitleAndPlatform(Set<String> fullTitles, Platform platform, Pageable pageable);

    Optional<Game> findGameById(Integer id, Platform platform);
}
