package com.trade4life.discs.trader.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trade4life.discs.trader.core.service.GamesService;
import com.trade4life.discs.trader.core.service.dto.Game;
import com.trade4life.discs.trader.core.service.dto.Games;
import com.trade4life.discs.trader.core.service.dto.Platform;
import com.trade4life.discs.trader.core.service.dto.Title;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(value = "core-games", tags = "core-games")
@Validated
public class GamesController {
    private final GamesService gamesService;
    private static final Logger LOGGER = LoggerFactory.getLogger(GamesController.class);

    @ApiOperation(value = "Get the list of game titles by platform and title text part", nickname = "getGameTitles")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "{platform}/games/titles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Title> getGameTitles(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                               @PathVariable(name = "platform") @NotNull Platform platform,
                                               @ApiParam(name = "titleText", value = "Game title text", example = "The Witcher 3")
                                               @RequestParam(name = "titleText", required = false) String titleText,
                                               @ApiParam(name = "propositionSize", value = "Number of title propositions (1..N)", defaultValue = "5")
                                               @RequestParam(name = "propositionSize", defaultValue = "5") @Positive Integer propositionSize) {
        Title title = gamesService.findGameTitle(titleText, platform, propositionSize);
        if (StringUtils.isEmpty(title.getTitle())) {
            return new ResponseEntity<>(title, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @ApiOperation(value = "Get the list of games by platform", nickname = "getGames")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "{platform}/games", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Games> getGames(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                          @PathVariable(name = "platform") @NotNull Platform platform,
                                          @ApiParam(name = "fullTitles", value = "Full game titles", example = "The Witcher 3: Wild Hunt")
                                          @RequestParam(name = "fullTitles", required = false) Set<String> fullTitles,
                                          @ApiParam(name = "page", value = "Page number (1..N)", defaultValue = "1")
                                          @RequestParam(name = "page") @NotNull @Positive Integer page,
                                          @ApiParam(name = "size", value = "Number of records per page (1..N)", defaultValue = "5")
                                          @RequestParam(name = "size") @NotNull @Positive Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Games games = gamesService.findGamesByTitleAndPlatform(fullTitles, platform, pageable);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @ApiOperation(value = "Get the game by platform and game id", nickname = "getGameById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "{platform}/games/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> getGames(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                         @PathVariable(name = "platform") @NotNull Platform platform,
                                         @ApiParam(name = "gameId", value = "Game id", example = "1", required = true)
                                         @PathVariable(name = "gameId") @NotNull @Positive Integer gameId) {
        Game game = gamesService.findGameById(gameId, platform);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
