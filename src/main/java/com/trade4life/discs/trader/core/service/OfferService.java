package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.domain.Offer;
import com.trade4life.discs.trader.core.domain.OfferStatus;
import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Pageable;

public interface OfferService {
    OfferGamesResponse findOffersByStatus(OfferStatus offerStatus, Pageable pageable);

    OfferGamesResponse findOffersByStatusAndTelegramId(OfferStatus offerStatus, String telegramId, Pageable pageable);

    Offer findOfferById(String offerId);

    OfferGamesResponse findOfferByGameId(String gameId, Pageable pageable);

    Offer addNewOffer(Offer offer);

    Offer updateOffer(Offer offer);

    void deleteOffer(String offerId);
}
