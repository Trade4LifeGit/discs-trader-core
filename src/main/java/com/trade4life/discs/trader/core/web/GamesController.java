package com.trade4life.discs.trader.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.service.GamesService;
import com.trade4life.discs.trader.core.service.dto.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(value = "core-games", tags = "core-games")
@Validated
public class GamesController {
    private final GamesService gamesService;
    private static final Logger LOGGER = LoggerFactory.getLogger(GamesController.class);

    @ApiOperation(value = "Get the list of game propositions by platform and title text part", nickname = "getGamePropositions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "{platform}/games/propositions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GamePropositionResponse> getGameTitles(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                               @PathVariable(name = "platform") @NotNull Platform platform,
                                               @ApiParam(name = "titleText", value = "Game title text", example = "The Witcher 3")
                                               @RequestParam(name = "titleText", required = false) String titleText,
                                               @ApiParam(name = "propositionSize", value = "Number of title propositions (1..N)", defaultValue = "5")
                                               @RequestParam(name = "propositionSize", defaultValue = "5") @Positive Integer propositionSize) {
        GamePropositionResponse gamePropositionResponse = gamesService.findGameByTitlePartAndPlatform(titleText, platform, propositionSize);
        if (gamePropositionResponse.getGame() == null) {
            return new ResponseEntity<>(gamePropositionResponse, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gamePropositionResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get the list of games by platform", nickname = "getGames")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "{platform}/games", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponse> getGames(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                          @PathVariable(name = "platform") @NotNull Platform platform,
                                          @ApiParam(name = "titlePart", value = "Game title part", example = "The Witche")
                                          @RequestParam(name = "titlePart", required = false) String titlePart,
                                          @ApiParam(name = "page", value = "Page number (0..N)", defaultValue = "0")
                                          @RequestParam(name = "page") @NotNull Integer page,
                                          @ApiParam(name = "size", value = "Number of records per page (0..N)", defaultValue = "5")
                                          @RequestParam(name = "size") @NotNull @Positive Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        GameResponse gameResponse = gamesService.findAllGamesByTitlePartAndPlatform(titlePart, platform, pageable);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get the game by platform and game id", nickname = "getGameById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "{platform}/games/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> getGame(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                        @PathVariable(name = "platform") @NotNull Platform platform,
                                        @ApiParam(name = "gameId", value = "Game id", example = "5f70948f17361f2260cb22aa", required = true)
                                        @PathVariable(name = "gameId") @NotBlank String gameId) {
        Game game = gamesService.findGameById(gameId, platform);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
