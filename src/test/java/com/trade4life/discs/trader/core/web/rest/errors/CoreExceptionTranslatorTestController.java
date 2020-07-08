package com.trade4life.discs.trader.core.web.rest.errors;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/exception-translator-test")
public class CoreExceptionTranslatorTestController {

    @PostMapping("/method-argument")
    public void methodArgument(@Valid @RequestBody TestDTO testDTO) {
    }

    @GetMapping("/missing-servlet-request-part")
    public void missingServletRequestPartException(@RequestPart String part) throws MissingServletRequestPartException {
        throw new MissingServletRequestPartException(part);
    }

    @GetMapping("/missing-servlet-request-parameter")
    public void missingServletRequestParameterException(@RequestParam String param) throws MissingServletRequestParameterException {
        throw new MissingServletRequestParameterException(param, "String");
    }

    @GetMapping("/access-denied")
    public void accessdenied() {
        throw new AccessDeniedException("test access denied!");
    }

    @GetMapping("/unauthorized")
    public void unauthorized() {
        throw new BadCredentialsException("test authentication failed!");
    }

    @GetMapping("/internal-server-error")
    public void internalServerError() {
        throw new RuntimeException();
    }

    public static class TestDTO {

        @NotNull
        private String test;

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }

}
