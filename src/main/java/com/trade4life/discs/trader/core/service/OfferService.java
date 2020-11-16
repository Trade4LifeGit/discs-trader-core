package com.trade4life.discs.trader.core.service;

import com.trade4life.discs.trader.core.domain.Offer;
import com.trade4life.discs.trader.core.domain.OfferStatus;
import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Pageable;

public interface OfferService {
    OfferResponse findOffersByStatus(OfferStatus offerStatus, Pageable pageable);

    Offer findOfferById(String offerId);

    Offer addNewOffer(Offer offer);

    Offer updateOffer(Offer offer);
}
