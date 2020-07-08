package com.trade4life.discs.trader.core.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum  CoreInternalErrorCode {
    MISSING_MANDATORY_PARAMETER(11, Constants.MISSING_MANDATORY_PARAMETER, "Not found some of mandatory parameter"),

    STEALER_CLIENT_ERROR(32, Constants.STEALER_CLIENT_ERROR, "Unexpected error in stealer client"),
    GAMES_CLIENT_ERROR(33, Constants.GAMES_CLIENT_ERROR, "Unexpected error in games client"),
    USERS_CLIENT_ERROR(34, Constants.USERS_CLIENT_ERROR, "Unexpected error in users client"),
    OFFERS_CLIENT_ERROR(35, Constants.OFFERS_CLIENT_ERROR, "Unexpected error in offers client"),

    METHOD_ARGUMENT_EXCEPTION(10, Constants.METHOD_ARGUMENT_EXCEPTION, "Method argument not valid exception"),
    INTERNAL_SYSTEM_ERROR_DEFAULT(300, Constants.INTERNAL_ERROR, "Unexpected internal system error"),

    ACCESS_DENIED(301, Constants.ACCESS_DENIED, "Access denied"),
    UNAUTHORIZED_REQUEST(302, Constants.UNAUTHORIZED, "Unauthorized request"),

    GAME_NOT_FOUND(1106, Constants.GAME_NOT_FOUND, "Game can't be found"),
    USER_NOT_FOUND(1107, Constants.USER_NOT_FOUND, "User can't be found"),
    OFFER_NOT_FOUND(1108, Constants.OFFER_NOT_FOUND, "Offer can't be found"),
    ;

    private final Integer coreErrorCode;
    private final String coreErrorCodeStr;
    private final String message;

    public static boolean isClientError(CoreInternalErrorCode errorCode) {
        return List.of(STEALER_CLIENT_ERROR, GAMES_CLIENT_ERROR, USERS_CLIENT_ERROR, OFFERS_CLIENT_ERROR).contains(errorCode);
    }

    private static class Constants {
        private static final String ACCESS_DENIED = "ACCESS_DENIED";
        private static final String UNAUTHORIZED = "UNAUTHORIZED";
        private static final String MISSING_MANDATORY_PARAMETER = "MISSING_MANDATORY_PARAMETER";

        private static final String STEALER_CLIENT_ERROR = "STEALER_CLIENT_ERROR";
        private static final String GAMES_CLIENT_ERROR = "GAMES_CLIENT_ERROR";
        private static final String USERS_CLIENT_ERROR = "USERS_CLIENT_ERROR";
        private static final String OFFERS_CLIENT_ERROR = "OFFERS_CLIENT_ERROR";

        private static final String METHOD_ARGUMENT_EXCEPTION = "METHOD_ARGUMENT_EXCEPTION";
        private static final String INTERNAL_ERROR = "INTERNAL_ERROR";

        private static final String GAME_NOT_FOUND = "GAME_NOT_FOUND";
        private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
        private static final String OFFER_NOT_FOUND = "OFFER_NOT_FOUND";
    }
}
