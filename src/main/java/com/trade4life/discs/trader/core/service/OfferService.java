package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface OfferService {
    //offers by user--- to check could i buy something else for one seller
    Page<Offer> findOffersByUserId(String userId, Pageable pageable, Sort sort);

    //offers by game
    Page<Offer> findOffersByGameId(String gameId, Pageable pageable, Sort sort);

    //offers by region and platform
    Page<Offer> findOffersByRegionAndPlatform(Region region, Platform platform, Pageable pageable, Sort sort);

    //offers by region
    Page<Offer> findOffersByRegion(Region region, Pageable pageable, Sort sort);

    //offers by type
    Page<Offer> findOffersByType(OfferType offerType, Pageable pageable, Sort sort);

    //offers by status
    Page<Offer> findOffersByStatus(OfferStatus offerStatus, Pageable pageable, Sort sort);



    //!!!!!!
    Page<Offer> findOffersByParams(OfferSearchParams offerSearchParams, Pageable pageable);

    Optional<Offer> findOffersById(String offerId);
}
