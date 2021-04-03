package com.trade4life.discs.trader.core.service.dto;

import com.trade4life.discs.trader.core.domain.Offer;
import com.trade4life.discs.trader.core.domain.Platform;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OfferGamesResponse", description = "Offer games search result")
public class OfferGamesResponse {
    @ApiModelProperty(position = 1, allowableValues = "PSN, ESHOP", example = "PSN")
    private Platform platform;
    @ApiModelProperty(position = 2, example = "1")
    private Integer page;
    @ApiModelProperty(position = 3, example = "3")
    private Integer size;
    @ApiModelProperty(position = 4, example = "5")
    private Integer totalPages;
    @ApiModelProperty(position = 5, example = "7")
    private Long totalOfferGames;
    @ApiModelProperty(position = 6)
    private Set<GameWithRelatedOffers> offerGames;
    @ApiModelProperty(position = 7)
    private Set<Offer> offers;
}
