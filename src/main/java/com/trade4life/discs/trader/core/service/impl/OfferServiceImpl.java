package com.trade4life.discs.trader.core.service.impl;

import com.trade4life.discs.trader.core.service.OfferService;
import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class OfferServiceImpl implements OfferService {
    @Override
    public Page<Offer> findOffersByUserId(String userId, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findOffersByGameId(String gameId, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findOffersByRegionAndPlatform(Region region, Platform platform, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findOffersByRegion(Region region, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findOffersByType(OfferType offerType, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findOffersByStatus(OfferStatus offerStatus, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findOffersByParams(OfferSearchParams offerSearchParams, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Offer> findOffersById(String offerId) {
        return Optional.empty();
    }
}
