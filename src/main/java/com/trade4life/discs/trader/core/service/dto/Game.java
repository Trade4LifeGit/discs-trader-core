package com.trade4life.discs.trader.core.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Game", description = "Game information")
public class Game {
    @ApiModelProperty(position = 1, example = "1")
    private Integer id;
    @ApiModelProperty(position = 2, example = "The Witcher 3: Wild Hunt")
    private String title;
    @ApiModelProperty(position = 3, example = "description")
    private String description;
    @ApiModelProperty(position = 4, allowableValues = "PSN, ESHOP", example = "PSN")
    private Platform platform;
    @ApiModelProperty(position = 5, example = "https://bit.ly/2C1oaH3")
    private String image;
}
