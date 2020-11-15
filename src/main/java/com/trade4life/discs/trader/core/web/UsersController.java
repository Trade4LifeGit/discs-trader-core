package com.trade4life.discs.trader.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trade4life.discs.trader.core.service.UserService;
import com.trade4life.discs.trader.core.domain.Platform;
import com.trade4life.discs.trader.core.domain.User;
import com.trade4life.discs.trader.core.service.dto.UserResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(value = "core-users", tags = "core-users")
@Validated
public class UsersController {

    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StealerController.class);

    @ApiOperation(value = "Get users", nickname = "getUsers")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUsers(@ApiParam(name = "page", value = "Page number (1..N)", defaultValue = "1")
                                          @RequestParam(name = "page") @NotNull @Positive Integer page,
                                                 @ApiParam(name = "size", value = "Number of records per page (1..N)", defaultValue = "5")
                                          @RequestParam(name = "size") @NotNull @Positive Integer size) {
        if (page > 0) {
            page = page - 1;
        }
        Pageable pageable = PageRequest.of(page, size);
        List<User> users = userService.findUsers(pageable);

        UserResponse userResponse = UserResponse.builder()
            .page(page)
            .size(size)
            .users(users)
            .build();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by telegramId", nickname = "getUserByTelegramId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "users/{telegramId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByTelegramId(@ApiParam(name = "telegramId", value = "Telegram id", example = "dfdgra", required = true)
                                                    @PathVariable(name = "telegramId") @NotBlank String telegramId) {
        User telegramUser = userService.findUserByTelegramId(telegramId);
        return new ResponseEntity<>(telegramUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Get telegram user by nickname", nickname = "getUserByNickname")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "{platform}/users/{nickname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByNicknameAndPlatform(@ApiParam(name = "platform", value = "Platform identifier", allowableValues = "PSN, ESHOP", defaultValue = "PSN", required = true)
                                            @PathVariable(name = "platform") @NotNull Platform platform,
                                            @ApiParam(name = "nickname", value = "User nickname", example = "1", required = true)
                                            @PathVariable(name = "nickname") @NotNull @Positive String nickname) {
        User user = userService.findUserByNickname(nickname);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Register new telegram user", nickname = "registerNewUser")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerNewUser(@ApiParam(name = "user", value = "User information", defaultValue = "1")
                                                @RequestBody @NotNull User user) {
        User newUser = userService.addNewUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the user information", nickname = "updateUser")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Updated"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(value = "users/{telegramId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@ApiParam(name = "user", value = "User information", defaultValue = "1")
                                           @RequestBody @NotNull User user,
                                           @ApiParam(name = "telegramId", value = "Telegram id", example = "errssgggeeq", required = true)
                                           @PathVariable(name = "telegramId") @NotBlank String telegramId) {
        user.setTelegramId(telegramId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.NO_CONTENT);
    }
}
