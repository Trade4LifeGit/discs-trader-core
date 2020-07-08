package com.trade4life.discs.trader.core.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoreException extends RuntimeException implements CoreInternalError {
    private final CoreInternalErrorCode errorCode;
    private final HttpStatus httpStatus;

    public CoreException(CoreInternalErrorCode errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CoreException(String message, CoreInternalErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CoreException(String message, Throwable cause, CoreInternalErrorCode errorCode, HttpStatus httpStatus) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CoreException(Throwable cause, CoreInternalErrorCode errorCode, HttpStatus httpStatus) {
        super(cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CoreInternalErrorCode errorCode, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    @Override
    public CoreInternalErrorCode getInternalError() {
        return errorCode;
    }
}
