package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.repository.GamesRepository;
import com.trade4life.discs.trader.core.service.GamesService;
import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Title;
import com.trade4life.discs.trader.core.service.dto.Games;
import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.GAME_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {
    private final GamesRepository gamesRepository;

    @Override
    public Title findGameTitle(String titleText, Platform platform, Integer propositionSize) {
        String title = gamesRepository.findGameTitle(titleText, platform)
            .orElse(null);

        Set<String> propositions = gamesRepository.findGameTitlePropositions(titleText, platform, propositionSize);

        return Title.builder()
            .title(title)
            .propositions(propositions)
            .build();
    }

    @Override
    public Games findGamesByTitleAndPlatform(Set<String> gameTitles, Platform platform, Pageable pageable) {
        List<Game> games = gamesRepository.findGamesByTitleAndPlatform(gameTitles, platform, pageable);
        return Games.builder()
            .platform(platform)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .games(games)
            .build();
    }

    @Override
    public Game findGameById(Integer id, Platform platform) {
        return gamesRepository.findGameById(id, platform)
            .orElseThrow(() -> new CoreException(GAME_NOT_FOUND, NOT_FOUND));
    }
}
