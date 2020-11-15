package com.trade4life.discs.trader.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(value = "games.title")
@ApiModel(value = "Title", description = "Game title search result")
public class Title {
    @ApiModelProperty(position = 1, example = "The Witcher 3: Wild Hunt")
    private String title;
}
