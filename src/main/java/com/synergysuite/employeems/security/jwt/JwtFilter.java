package com.synergysuite.employeems.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        try
        {
            String jwt = resolveToken(request);
            if (StringUtils.isNotBlank(jwt) && tokenProvider.validateToken(jwt))
            {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication); // ThreadLocal (T1)
            }
            filterChain.doFilter(request, response); // SecurityContextHolder - Authentication
        }
        catch (ExpiredJwtException e) {
            prepareExpiredTokenResponse(response, e);
        }
    }

    /**
     * Resolving token from HttpServletRequest
     *
     * @param request http servlet request
     * @return token as string | null
     */
    private String resolveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization"); // "Bearer token-value"
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "token-value"
        }
        return null;
    }

    private void prepareExpiredTokenResponse(HttpServletResponse response, ExpiredJwtException e) throws IOException
    {
        ExpiredJwtDTO expiredJwtDTO = new ExpiredJwtDTO(e.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 406
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(expiredJwtDTO)
        );
    }

    private record ExpiredJwtDTO(String message) { }
}
