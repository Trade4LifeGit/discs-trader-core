package com.trade4life.discs.trader.core.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Title", description = "Game title search result")
public class Title {
    @ApiModelProperty(position = 1, example = "The Witcher 3: Wild Hunt")
    private String title;
    @ApiModelProperty(position = 2, example = "The Witcher 3: Wild Hunt — Hearts of Stone")
    private Set<String> propositions;
}
