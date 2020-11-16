package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OfferRepository extends MongoRepository<Offer, String> {
    Page<Offer> findOffersByStatus(OfferStatus offerStatus, Pageable pageable);

    Optional<Offer> findOfferById(String id);
}
