package com.trade4life.discs.trader.core.repository;

import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Title;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GameRepository extends MongoRepository<Game, String> {

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}", fields = "{title : 1}")
    List<Title> findTitlesByText(String titleText, Pageable pageable);

    @Query(value = "{'title': {$ne : null}}", fields = "{title : 1}")
    List<Title> findAllTitles(Pageable pageable);

    @Query(value = "{'title': {$ne : null}}")
    List<Game> findAllGames(Pageable pageable);

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    List<Game> findGamesByTitlePart(String titlePart, Pageable pageable);

    @Query(value = "{'title': {$in : [?0]}}")
    List<Game> findGamesByTitleIn(Set<String> fullTitles, Pageable pageable);

    Optional<Game> findGameById(String id);
}