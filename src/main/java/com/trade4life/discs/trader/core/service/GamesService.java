package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Pageable;

public interface GamesService {
    GamePropositionResponse findGameByTitlePartAndPlatform(String titlePart, Platform platform, Integer size);

    GameResponse findAllGamesByTitlePartAndPlatform(String titlePart, Platform platform, Pageable pageable);

    Game findGameById(String id, Platform platform);
}
