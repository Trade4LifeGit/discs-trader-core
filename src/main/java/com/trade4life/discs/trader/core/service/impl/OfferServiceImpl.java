package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Offer;
import com.trade4life.discs.trader.core.domain.OfferStatus;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.repository.GameRepository;
import com.trade4life.discs.trader.core.repository.OfferRepository;
import com.trade4life.discs.trader.core.service.OfferService;
import com.trade4life.discs.trader.core.service.dto.*;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.OFFER_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final GameRepository gameRepository;

    @Override
    public OfferResponse findOffersByStatus(OfferStatus offerStatus, Pageable pageable) {

        Page<Offer> publishedOffers = offerRepository.findOffersByStatus(OfferStatus.PUBLISHED, pageable);
        Set<String> gameIds = publishedOffers.getContent().stream()
            .map(Offer::getGameId)
            .collect(Collectors.toSet());

        Set<Game> games = gameRepository.findGamesByIdIn(gameIds);

        Map<String, List<Offer>> offersByGameId = publishedOffers.getContent().stream()
            .sorted(Comparator.comparing(Offer::getGameId).thenComparing(Offer::getPrice))
            .collect(Collectors.groupingBy(Offer::getGameId));

        return OfferResponse.builder()
            .platform(Platform.PSN)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .totalPages(publishedOffers.getTotalPages())
            .totalOffers(publishedOffers.getTotalElements())
            .offerGames(games)
            .offersByGameId(offersByGameId)
            .build();
    }

    @Override
    public Offer findOfferById(String offerId) {
        return offerRepository.findOfferById(offerId)
            .orElseThrow(() -> new CoreException(OFFER_NOT_FOUND, NOT_FOUND));
    }

    @Override
    public Offer addNewOffer(Offer offer) {
        if (offer.getId() != null) {
            return updateOffer(offer);
        }
        offer.setStatus(OfferStatus.PUBLISHED);
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Offer offer) {
        offerRepository.findOfferById(offer.getId())
            .orElseThrow(() -> new CoreException(OFFER_NOT_FOUND, NOT_FOUND));
        return offerRepository.save(offer);
    }
}
