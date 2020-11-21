package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.repository.GameRepository;
import com.trade4life.discs.trader.core.service.GamesService;
import com.trade4life.discs.trader.core.service.dto.*;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import com.trade4life.discs.trader.core.service.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.GAME_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {
    private final GameRepository gameRepository;
    private final ResponseMapper responseMapper;

    @Override
    public GamePropositionResponse findGameByTitlePartAndPlatform(String titleText, Platform platform, Integer propositionSize) {
        Page<Game> propositionsPage = StringUtils.isNotBlank(titleText) ?
            gameRepository.findGamesByTitlePart(titleText, PageRequest.of(0, propositionSize)) :
            gameRepository.findAllGames(PageRequest.of(0, propositionSize));

        List<Game> pageContent = propositionsPage.getContent();
        boolean isOneLineSearchResult = propositionSize > 1 && pageContent.size() == 1;

        Game game = isOneLineSearchResult ? pageContent.get(0) : null;
        return responseMapper.toGamePropositionResponse(pageContent, game, platform);
    }

    @Override
    public GameResponse findAllGamesByTitlePartAndPlatform(String titlePart, Platform platform, Pageable pageable) {
        Page<Game> gamesPage = StringUtils.isBlank(titlePart) ? gameRepository.findAllGames(pageable) :
            gameRepository.findGamesByTitlePart(titlePart, pageable);

        return responseMapper.toGameResponse(gamesPage, platform, pageable);
    }

    @Override
    public Game findGameById(String id, Platform platform) {
        return gameRepository.findGameById(id)
            .orElseThrow(() -> new CoreException(GAME_NOT_FOUND, NOT_FOUND));
    }
}
