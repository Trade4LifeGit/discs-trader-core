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
@ApiModel(value = "TitleResponse", description = "Title search result")
public class TitleResponse {
    @ApiModelProperty(position = 1, allowableValues = "PSN, ESHOP")
    private Platform platform = Platform.PSN;
    @ApiModelProperty(position = 2, example = "The Witcher 3: Wild Hunt")
    private String title;
    @ApiModelProperty(position = 3, example = "The Witcher 3: Wild Hunt — Hearts of Stone")
    private Set<String> propositions;
}
