package com.trade4life.discs.trader.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "games")
@ApiModel(value = "Game", description = "Game information")
public class Game {
    @ApiModelProperty(position = 1, example = "5f70948f17361f2260cb22a7")
    private String id;
    @ApiModelProperty(position = 2, example = "UP0082-CUSA18774_00-0000000000000000")
    private String ps4Id;
    @ApiModelProperty(position = 3, example = "The Witcher 3: Wild Hunt")
    private String title;
    @JsonIgnore
    @ApiModelProperty(position = 4, example = "description")
    private String description;
    @ApiModelProperty(position = 5, allowableValues = "PSN, ESHOP")
    private Platform platform = Platform.PSN;
    @ApiModelProperty(position = 6, example = "https://store.playstation.com/store/api/chihiro/00_09_000/container/US/en/999/UP0082-CUSA18774_00-0000000000000000/1601080823000/image")
    private String image;
    @ApiModelProperty(position = 7, example = "https://store.playstation.com/en-us/product/UP0082-CUSA18774_00-0000000000000000")
    private String psnURL;
}
