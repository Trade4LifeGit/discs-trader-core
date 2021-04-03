package com.trade4life.discs.trader.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trade4life.discs.trader.core.domain.Offer;
import com.trade4life.discs.trader.core.domain.OfferStatus;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.service.OfferService;
import com.trade4life.discs.trader.core.service.dto.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/{platform}/")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(value = "core-offers", tags = "core-offers")
public class OffersController {
    private final OfferService offerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(GamesController.class);

    @ApiOperation(value = "Get the list of published offers", nickname = "getPublishedOffers")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Error")
    })
    @GetMapping(value = "offers/published", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferGamesResponse> getPublishedOffers(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                                                    @PathVariable(name = "platform") @NotNull Platform platform,
                                                                    @ApiParam(name = "gameId", value = "game id")
                                                                    @RequestParam(name = "gameId", required = false) String gameId,
                                                                    @ApiParam(name = "page", value = "Page number (0..N)", defaultValue = "0")
                                                                    @RequestParam(name = "page") @NotNull Integer page,
                                                                    @ApiParam(name = "size", value = "Number of records per page (0..N)", defaultValue = "5")
                                                                    @RequestParam(name = "size") @NotNull @Positive Integer size ) {
        Pageable pageable = PageRequest.of(page, size);

        if (!StringUtils.isBlank(gameId)){
            OfferGamesResponse offers = offerService.findOfferByGameId(gameId, pageable);
            return new ResponseEntity<>(offers, HttpStatus.OK);
        }

        OfferGamesResponse publishedOfferGames = offerService.findOffersByStatus(OfferStatus.PUBLISHED, pageable);
        return new ResponseEntity<>(publishedOfferGames, HttpStatus.OK);
    }

    @ApiOperation(value = "Publish new game offer", nickname = "publishOffer")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> publishOffer(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                              @PathVariable(name = "platform") @NotNull Platform platform,
                                              @ApiParam(name = "offer", value = "Offer") @RequestBody @NotNull Offer offer) {

        Offer newOffer = offerService.addNewOffer(offer);
        return new ResponseEntity<>(newOffer, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the offer", nickname = "updateOffer")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Updated"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(value = "offers/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> updateOffer(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                             @PathVariable(name = "platform") @NotNull Platform platform,
                                             @ApiParam(name = "offer", value = "Offer") @RequestBody @NotNull Offer offer,
                                             @ApiParam(name = "offerId", value = "Offer id", required = true)
                                             @PathVariable(name = "offerId") @NotBlank String offerId) {
        offer.setId(offerId);
        Offer updatedOffer = offerService.updateOffer(offer);
        return new ResponseEntity<>(updatedOffer, HttpStatus.NO_CONTENT);
    }
}
