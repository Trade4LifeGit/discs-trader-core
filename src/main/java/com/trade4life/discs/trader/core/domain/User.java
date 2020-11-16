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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "users")
@ApiModel(value = "User", description = "User information")
public class User {
    @ApiModelProperty(position = 1, example = "5f7f6398c0418a5b8c52bbce")
    @JsonIgnore
    private String id;
    @ApiModelProperty(position = 2, example = "dsvmwejfisvskmsa")
    private String telegramId;
    @ApiModelProperty(position = 3, example = "@puzzleqw")
    private String nickname;
    @ApiModelProperty(position = 4, example = "Stanislau K.")
    private String name;
    @ApiModelProperty(position = 5, example = "+375296660818")
    private String phone;
    @ApiModelProperty(position = 6, example = "+375296660818")
    private String bio;
    @ApiModelProperty(position = 7, allowableValues = "PSN, ESHOP")
    private Platform platform;
    @ApiModelProperty(position = 8)
    private Boolean isBlocked;
}
