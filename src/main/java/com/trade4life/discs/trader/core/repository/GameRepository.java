package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String> {

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}", fields = "{title : 1}")
    Page<Title> findTitlesByText(String titleText, Pageable pageable);

    @Query(value = "{'title': {$ne : null}}", fields = "{title : 1}")
    Page<Title> findAllTitles(Pageable pageable);

    @Query(value = "{'title': {$ne : null}}")
    Page<Game> findAllGames(Pageable pageable);

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    Page<Game> findGamesByTitlePart(String titlePart, Pageable pageable);

    Optional<Game> findGameById(String id);
}
