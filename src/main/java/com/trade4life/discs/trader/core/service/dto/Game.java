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
    @ApiModelProperty(position = 2, example = "Ведьмак 3: Дикая Охота")
    private String title;
    @ApiModelProperty(position = 3, example = "Плотва и Геральд в лесу")
    private String description;
    @ApiModelProperty(position = 4, allowableValues = "PSN, ESHOP", example = "psn")
    private Platform platform;
    @ApiModelProperty(position = 5, example = "https://bit.ly/2C1oaH3")
    private String image;
}
