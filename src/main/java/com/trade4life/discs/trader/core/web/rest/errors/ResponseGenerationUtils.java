package com.trade4life.discs.trader.core.web.rest.errors;

import com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseGenerationUtils {

    public static ResponseEntity<Map<String, String>> buildErrorResponse(CoreInternalErrorCode internalErrorCode, HttpStatus errorStatus) {
        return buildErrorResponse(internalErrorCode, internalErrorCode.getMessage(), errorStatus);
    }

    public static ResponseEntity<Map<String, String>> buildErrorResponse(CoreInternalErrorCode internalErrorCode, String message, HttpStatus errorStatus) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("errorCode", internalErrorCode.getCoreErrorCode().toString());
        responseMap.put("errorCodeStr", internalErrorCode.getCoreErrorCodeStr());
        responseMap.put("message", message);
        return new ResponseEntity<>(responseMap, errorStatus);
    }
}
