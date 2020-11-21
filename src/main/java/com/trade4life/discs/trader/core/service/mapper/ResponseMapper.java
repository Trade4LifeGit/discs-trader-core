package com.trade4life.discs.trader.core.service.mapper;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.service.dto.GamePropositionResponse;
import com.trade4life.discs.trader.core.service.dto.GameResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel="spring")
public interface ResponseMapper {

    default GameResponse toGameResponse(Page<Game> gamesPage, Platform platform, Pageable pageable) {
        return GameResponse.builder()
            .platform(platform)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .totalPages(gamesPage.getTotalPages())
            .totalGames(gamesPage.getTotalElements())
            .games(gamesPage.getContent())
            .build();
    }

    default GamePropositionResponse toGamePropositionResponse(List<Game> propositions, Game game, Platform platform) {
        return GamePropositionResponse.builder()
            .platform(platform)
            .game(game)
            .propositions(propositions)
            .build();
    }
}
