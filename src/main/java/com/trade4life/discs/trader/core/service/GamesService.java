package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Games;
import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.dto.Title;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface GamesService {
    Title findGameTitle(String titleText, Platform platform, Integer size);

    Games findGamesByTitleAndPlatform(Set<String> fullTitles, Platform platform, Pageable pageable);

    Game findGameById(Integer id, Platform platform);
}
