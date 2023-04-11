package com.synergysuite.employeems.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnauthorizedHttpEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException
    {
        UnauthroizedHttpResponse unauthroizedHttpResponse = new UnauthroizedHttpResponse(authException.getMessage());
        String rawResponse = new ObjectMapper().writeValueAsString(unauthroizedHttpResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(rawResponse);
    }

    @Data
    @AllArgsConstructor
    static
    class UnauthroizedHttpResponse {
        private String message;
    }
}
