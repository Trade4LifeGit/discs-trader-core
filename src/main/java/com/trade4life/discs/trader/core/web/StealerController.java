package com.trade4life.discs.trader.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(value = "core-stealer", tags = "core-stealer")
public class StealerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StealerController.class);

    @ApiOperation(value = "Trigger the PSN stealer for games parsing", nickname = "stealPsn")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Found cos list"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "/psn/steal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> stealPsn() {
        return new ResponseEntity(null);
    }
}
