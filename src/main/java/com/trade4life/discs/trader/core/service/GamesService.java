package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface GamesService {
    TitleResponse findGameTitle(String titlePart, Platform platform, Integer size);

    GamesResponse findGamesByTitleAndPlatform(Set<String> fullTitles, Platform platform, Pageable pageable);

    GamesResponse findGamesByTitlePartAndPlatform(String titlePart, Platform platform, Pageable pageable);

    Game findGameById(String id, Platform platform);
}
