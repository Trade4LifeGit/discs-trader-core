package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfferRepository extends MongoRepository<Offer, String> {
    /// SORT BY PRICE FOR EACH


    //offers by user--- to check could i buy something else for one seller

    //offers by game

    //offers by region and platform

    //offers by region

    //offers by type

    //offers by status

    //add update delete
}
