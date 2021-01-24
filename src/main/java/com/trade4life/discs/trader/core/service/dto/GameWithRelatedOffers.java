package com.trade4life.discs.trader.core.service.dto;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Offer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GameWithRelatedOffers", description = "Game with related public offers")
public class GameWithRelatedOffers {
    @ApiModelProperty(position = 1, allowableValues = "PSN, ESHOP", example = "PSN")
    private Game game;
    @ApiModelProperty(position = 2)
    private List<Offer> offers;
}
