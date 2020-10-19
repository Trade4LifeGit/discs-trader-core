package com.trade4life.discs.trader.core.service.dto;

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
@Document(collection = "offers")
@ApiModel(value = "Offer", description = "Offer information")
public class Offer {
    @ApiModelProperty(position = 1, example = "5f70948f17361f2260cb22a7")
    private String id;
    @ApiModelProperty(position = 2)
    private Game game;
    @ApiModelProperty(position = 3)
    private User user;
    @ApiModelProperty(position = 4, example = "PSN")
    private Platform platform;
    @ApiModelProperty(position = 5, example = "BELARUS")
    private Region region;
    @ApiModelProperty(position = 6, example = "BUY")
    private OfferType type;
    @ApiModelProperty(position = 7, example = "Open for swap")
    private String description;
    @ApiModelProperty(position = 8, example = "75")
    private Integer price;
    @ApiModelProperty(position = 9, example = "Minsk. Nemiga")
    private String location;
    @ApiModelProperty(position = 10, example = "PENDING")
    private OfferStatus status;
}