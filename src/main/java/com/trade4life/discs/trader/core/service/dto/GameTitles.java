package com.trade4life.discs.trader.core.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Titles", description = "Game titles")
public class GameTitles {
    @ApiModelProperty(position = 1, example = "The Witcher 3: Wild Hunt")
    private Set<String> titles;
    @ApiModelProperty(position = 2, example = "The Witcher 3: Wild Hunt — Hearts of Stone")
    private Set<String> propositions;
}
