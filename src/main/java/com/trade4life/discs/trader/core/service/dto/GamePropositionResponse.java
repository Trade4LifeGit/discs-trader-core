package com.trade4life.discs.trader.core.service.dto;

import com.trade4life.discs.trader.core.domain.Game;
import com.trade4life.discs.trader.core.domain.Platform;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GamePropositionResponse", description = "Game search proposition")
public class GamePropositionResponse {
    @ApiModelProperty(position = 1, allowableValues = "PSN, ESHOP")
    private Platform platform = Platform.PSN;
    @ApiModelProperty(position = 2)
    private Game game;
    @ApiModelProperty(position = 3)
    private List<Game> propositions;
}
