package com.trade4life.discs.trader.core.web.rest.errors;

import com.google.common.base.CaseFormat;
import com.trade4life.discs.trader.core.service.exception.CoreException;
import com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.*;
import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.UNAUTHORIZED_REQUEST;
import static com.trade4life.discs.trader.core.web.rest.errors.ResponseGenerationUtils.buildErrorResponse;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class CoreExceptionTranslator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoreExceptionTranslator.class);

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Map<String, String>> methodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        LOGGER.warn(ex.getMessage());
        return buildErrorResponse(METHOD_ARGUMENT_EXCEPTION, ex.getMessage(), BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldInCamel = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, error.getField());
            errors.add("'" + fieldInCamel + "' " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            String objectInCamel = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, error.getObjectName());
            errors.add("'" + objectInCamel + "' " + error.getDefaultMessage());
        }
        String errorMessage = StringUtils.join(errors, "; ");
        LOGGER.warn(errorMessage);
        return buildErrorResponse(METHOD_ARGUMENT_EXCEPTION, errorMessage, BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Map<String, String>> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        LOGGER.warn(ex.getMessage());
        return buildErrorResponse(MISSING_MANDATORY_PARAMETER, ex.getMessage(), BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<Map<String, String>> missingServletRequestPartException(MissingServletRequestPartException ex) {
        LOGGER.warn(ex.getMessage());
        return buildErrorResponse(MISSING_MANDATORY_PARAMETER, ex.getMessage(), BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Map<String, String>> insufficientScopeException(AccessDeniedException ex) {
        LOGGER.warn(ex.getMessage());
        return buildErrorResponse(ACCESS_DENIED, FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Map<String,String>> badCredentialsException(BadCredentialsException ex) {
        LOGGER.warn(ex.getMessage());
        return buildErrorResponse(UNAUTHORIZED_REQUEST, UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Map<String, String>> exceptionHandling(final Exception ex) {
        if (ex instanceof CoreException) {
            CoreException coreException = (CoreException) ex;
            if (CoreInternalErrorCode.isClientError(coreException.getInternalError())) {
                LOGGER.error(coreException.getMessage(), coreException);
                return buildErrorResponse(coreException.getInternalError(), coreException.getHttpStatus());
            }
            LOGGER.info(coreException.getInternalError().getMessage());
            return buildErrorResponse(coreException.getInternalError(), coreException.getHttpStatus());
        }

        LOGGER.error(ex.getMessage(), ex);
        return buildErrorResponse(INTERNAL_SYSTEM_ERROR_DEFAULT, INTERNAL_SERVER_ERROR);
    }
}
