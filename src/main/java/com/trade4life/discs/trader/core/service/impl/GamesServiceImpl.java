package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.repository.GameRepository;
import com.trade4life.discs.trader.core.service.GamesService;
import com.trade4life.discs.trader.core.service.dto.*;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
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
        Page<Title> propositionsPage = StringUtils.isNotBlank(titleText) ?
            gameRepository.findTitlesByText(titleText, PageRequest.of(0, propositionSize)) :
            gameRepository.findAllTitles(PageRequest.of(0, propositionSize));

        List<Title> pageContent = propositionsPage.getContent();
        boolean isOneLineSearchResult = propositionSize > 1 && pageContent.size() == 1;

        return TitleResponse.builder()
            .platform(platform)
            .title(isOneLineSearchResult ? pageContent.get(0).getTitle() : null)
            .propositions(pageContent.stream()
                .map(Title::getTitle)
                .collect(Collectors.toSet())
            )
            .build();
    }

    @Override
    public GamesResponse findGamesByTitlePartAndPlatform(String titlePart, Platform platform, Pageable pageable) {
        Page<Game> gamesPage = StringUtils.isBlank(titlePart) ? gameRepository.findAllGames(pageable) :
            gameRepository.findGamesByTitlePart(titlePart, pageable);

        List<Game> pageContent = gamesPage.getContent();
        return GamesResponse.builder()
            .platform(platform)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .totalPages(gamesPage.getTotalPages())
            .totalGames(gamesPage.getTotalElements())
            .games(pageContent)
            .build();
    }

    @Override
    public Game findGameById(String id, Platform platform) {
        return gameRepository.findGameById(id)
            .orElseThrow(() -> new CoreException(GAME_NOT_FOUND, NOT_FOUND));
    }
}
