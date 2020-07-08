package com.trade4life.discs.trader.core.web.rest.errors;

import com.trade4life.discs.trader.core.DiscsTraderCoreApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.trade4life.discs.trader.core.service.exception.CoreInternalErrorCode.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests {@link CoreExceptionTranslator} controller advice.
 */
@WithMockUser
@AutoConfigureMockMvc
@SpringBootTest(classes = DiscsTraderCoreApp.class)
public class CoreExceptionTranslatorIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMethodArgumentNotValid() throws Exception {
        mockMvc.perform(post("/api/exception-translator-test/method-argument").content("{}").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorCode").value(METHOD_ARGUMENT_EXCEPTION.getCoreErrorCode()))
            .andExpect(jsonPath("$.errorCodeStr").value(METHOD_ARGUMENT_EXCEPTION.getCoreErrorCodeStr()))
            .andExpect(jsonPath("$.message").value("'test' must not be null"));
    }

    @Test
    public void testMissingServletRequestPartException() throws Exception {
        mockMvc.perform(get("/api/exception-translator-test/missing-servlet-request-part"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorCode").value(MISSING_MANDATORY_PARAMETER.getCoreErrorCode()))
            .andExpect(jsonPath("$.errorCodeStr").value(MISSING_MANDATORY_PARAMETER.getCoreErrorCodeStr()))
            .andExpect(jsonPath("$.message").value("Required request part 'part' is not present"));
    }

    @Test
    public void testMissingServletRequestParameterException() throws Exception {
        mockMvc.perform(get("/api/exception-translator-test/missing-servlet-request-parameter"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorCode").value(MISSING_MANDATORY_PARAMETER.getCoreErrorCode()))
            .andExpect(jsonPath("$.errorCodeStr").value(MISSING_MANDATORY_PARAMETER.getCoreErrorCodeStr()))
            .andExpect(jsonPath("$.message").value("Required String parameter 'param' is not present"));
    }

    @Test
    public void testAccessDenied() throws Exception {
        mockMvc.perform(get("/api/exception-translator-test/access-denied"))
            .andExpect(status().isForbidden())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorCode").value(ACCESS_DENIED.getCoreErrorCode()))
            .andExpect(jsonPath("$.errorCodeStr").value(ACCESS_DENIED.getCoreErrorCodeStr()))
            .andExpect(jsonPath("$.message").value(ACCESS_DENIED.getMessage()));
    }

    @Test
    public void testUnauthorized() throws Exception {
        mockMvc.perform(get("/api/exception-translator-test/unauthorized"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorCode").value(UNAUTHORIZED_REQUEST.getCoreErrorCode()))
            .andExpect(jsonPath("$.errorCodeStr").value(UNAUTHORIZED_REQUEST.getCoreErrorCodeStr()))
            .andExpect(jsonPath("$.message").value(UNAUTHORIZED_REQUEST.getMessage()));
    }

    @Test
    public void testInternalServerError() throws Exception {
        mockMvc.perform(get("/api/exception-translator-test/internal-server-error"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorCode").value(INTERNAL_SYSTEM_ERROR_DEFAULT.getCoreErrorCode()))
            .andExpect(jsonPath("$.errorCodeStr").value(INTERNAL_SYSTEM_ERROR_DEFAULT.getCoreErrorCodeStr()))
            .andExpect(jsonPath("$.message").value(INTERNAL_SYSTEM_ERROR_DEFAULT.getMessage()));
    }

}
