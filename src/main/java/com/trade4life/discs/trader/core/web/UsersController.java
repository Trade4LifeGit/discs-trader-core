package com.trade4life.discs.trader.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(value = "core-users", tags = "core-users")
public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StealerController.class);

    @ApiOperation(value = "Say 'hi' to the user from server", nickname = "sayUserHi")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Found cos list"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "hi/test/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sayUserHi(@RequestParam String userId) {
        return new ResponseEntity<>("Hi from server, " + userId, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by userId and platform", nickname = "getUserById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Found"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "{platform}/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@RequestParam String userId) {
        return new ResponseEntity<>("blablabla, " + userId, HttpStatus.OK);
    }

    @ApiOperation(value = "Get users by platform", nickname = "getUsersByPlatform")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Found"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "{platform}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsersByPlatform(@RequestParam String userId) {
        return new ResponseEntity<>("blablabla, " + userId, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new user", nickname = "addNewUser")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "{platform}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addNewUser(@RequestParam String userId) {
        return new ResponseEntity<>("blablabla, " + userId, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the user information", nickname = "updateUser")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(value = "{platform}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@RequestParam String userId) {
        return new ResponseEntity<>("blablabla, " + userId, HttpStatus.OK);
    }

    @ApiOperation(value = "Block the user by userId", nickname = "blockUserById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(value = "{platform}/users/{userId}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> blockUserById(@RequestParam String userId) {
        return new ResponseEntity<>("blablabla, " + userId, HttpStatus.OK);
    }

    @ApiOperation(value = "Unblock the user by userId", nickname = "unblockUserById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(value = "{platform}/users/{userId}/unblock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> unblockUserById(@RequestParam String userId) {
        return new ResponseEntity<>("blablabla, " + userId, HttpStatus.OK);
    }
}
