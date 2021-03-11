package com.trade4life.discs.trader.core.service.mapper;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Offer;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.service.dto.GamePropositionResponse;
import com.trade4life.discs.trader.core.service.dto.GameResponse;
import com.trade4life.discs.trader.core.service.dto.GameWithRelatedOffers;
import com.trade4life.discs.trader.core.service.dto.OfferGamesResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
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

    default OfferGamesResponse toOfferGamesResponse(Set<Game> games, Page<Offer> publishedOffers, Platform platform, Pageable pageable) {

        Map<String, List<Offer>> offersByGameId = publishedOffers.getContent().stream()
            .sorted(Comparator.comparing(Offer::getGameId).thenComparing(Offer::getPrice))
            .collect(Collectors.groupingBy(Offer::getGameId));

        Set<GameWithRelatedOffers> offerGames = games.stream()
            .map(game ->
                GameWithRelatedOffers.builder()
                    .game(game)
                    .offers(offersByGameId.get(game.getId()))
                    .build()
            )
            .collect(Collectors.toSet());

        return OfferGamesResponse.builder()
            .platform(Platform.PSN)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .totalPages(publishedOffers.getTotalPages())
            .totalOfferGames(publishedOffers.getTotalElements())
            .offerGames(offerGames)
            .build();
    }

    default OfferGamesResponse toOfferGamesResponse(Page<Offer> publishedOffers, Platform platform, Pageable pageable) {

        Set<Offer> offers = publishedOffers.get().collect(Collectors.toSet());

        return OfferGamesResponse.builder()
            .platform(Platform.PSN)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .totalPages(publishedOffers.getTotalPages())
            .totalOfferGames(publishedOffers.getTotalElements())
            .offers(offers)
            .build();
    }
}
