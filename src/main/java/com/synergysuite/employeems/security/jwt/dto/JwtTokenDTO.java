package com.synergysuite.employeems.security.jwt.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtTokenDTO {

    private final String accessToken;
    private final String refreshToken;

}
