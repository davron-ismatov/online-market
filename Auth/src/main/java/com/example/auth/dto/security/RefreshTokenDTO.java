package com.example.auth.dto.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class RefreshTokenDTO {
    private UUID id;
    private String refreshToken;
    private String accessToken;
    private long expireRefreshToken;
    private long expireAccessToken;
}
