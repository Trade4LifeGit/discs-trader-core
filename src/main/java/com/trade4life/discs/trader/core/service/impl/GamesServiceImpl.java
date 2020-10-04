package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.repository.GameRepository;
import com.trade4life.discs.trader.core.service.GamesService;
import com.trade4life.discs.trader.core.service.dto.*;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.GAME_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {
    private final GameRepository gameRepository;

    @Override
    public TitleResponse findGameTitle(String titleText, Platform platform, Integer propositionSize) {
        List<Title> propositions = StringUtils.isNotBlank(titleText) ?
            gameRepository.findTitlesByText(titleText, PageRequest.of(0, propositionSize)) :
            gameRepository.findAllTitles(PageRequest.of(0, propositionSize));

        boolean isOneLineSearchResult = propositionSize > 1 && propositions.size() == 1;

        return TitleResponse.builder()
            .platform(platform)
            .title(isOneLineSearchResult ? propositions.get(0).getTitle() : null)
            .propositions(propositions.stream()
                .map(Title::getTitle)
                .collect(Collectors.toSet())
            )
            .build();
    }

    @Override
    public GamesResponse findGamesByTitlePartAndPlatform(String titlePart, Platform platform, Pageable pageable) {
        List<Game> games = StringUtils.isBlank(titlePart) ? gameRepository.findAllGames(pageable) :
            gameRepository.findGamesByTitlePart(titlePart, pageable);

        return GamesResponse.builder()
            .platform(platform)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .games(games)
            .build();
    }

    @Override
    public Game findGameById(String id, Platform platform) {
        return gameRepository.findGameById(id)
            .orElseThrow(() -> new CoreException(GAME_NOT_FOUND, NOT_FOUND));
    }
}
