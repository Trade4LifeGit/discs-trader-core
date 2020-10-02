package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGameRepository extends MongoRepository<Game, String> {
    //
}
