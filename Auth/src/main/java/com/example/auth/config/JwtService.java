package com.example.auth.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.auth.dto.security.RefreshTokenDTO;
import com.example.auth.entity.RefreshToken;
import com.example.auth.entity.Roles;
import com.example.auth.entity.Users;
import com.example.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtService {
    @Value(value = "${app.jwt.secret-key.access-token}")
    private String access_token;

    @Value(value = "${app.jwt.secret-key.refresh-token}")
    private String refresh_token;

    @Value(value = "${app.jwt.expiration.access-token}")
    private Long access_token_exp;

    @Value(value = "${app.jwt.expiration.refresh-token}")
    private Long refresh_token_exp;

    private final RefreshTokenRepository refreshTokenRepository;



    public String generateAccessToken(Long  id, String username, Set<Roles> roles){
        return JWT.create()
                .withSubject(String.valueOf(id))
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(Duration.ofMillis(access_token_exp)))
                .withClaim("username",username)
                .withClaim("roles", roles.stream().map(role -> role.getRole().name()).toList())
                .sign(Algorithm.HMAC256(access_token));
    }

    public RefreshTokenDTO saveRefreshToken(Users users){
        UUID uuid = UUID.randomUUID();
        String access_token = generateAccessToken(users.getId(), users.getUsername(), users.getRoles());
        String refresh_token = generateRefreshToken(users.getUsername(), users.getId(), uuid.toString());
        RefreshToken refreshToken = RefreshToken.builder()
                .accessToken(access_token)
                .refreshToken(refresh_token)
                .users(users)
                .expireRefreshToken(Instant.now().plus(Duration.ofMillis(refresh_token_exp)).toEpochMilli())
                .expireAccessToken(Instant.now().plus(Duration.ofMillis(access_token_exp)).toEpochMilli())
                .id(uuid)
                .build();
        refreshTokenRepository.save(refreshToken);
        return toRefreshTokenDTO(refreshToken);
    }
    public RefreshTokenDTO saveRefreshToken(UUID uuid,Users users){
        String access_token = generateAccessToken(users.getId(), users.getUsername(), users.getRoles());
        String refresh_token = generateRefreshToken(users.getUsername(), users.getId(), uuid.toString());
        RefreshToken refreshToken = RefreshToken.builder()
                .accessToken(access_token)
                .refreshToken(refresh_token)
                .users(users)
                .expireRefreshToken(Instant.now().plus(Duration.ofMillis(refresh_token_exp)).toEpochMilli())
                .expireAccessToken(Instant.now().plus(Duration.ofMillis(access_token_exp)).toEpochMilli())
                .id(uuid)
                .build();
        refreshTokenRepository.save(refreshToken);
        return toRefreshTokenDTO(refreshToken);
    }

    public String generateRefreshToken(String username,Long userId,String tokenId){
        return JWT.create()
                .withSubject(tokenId)
                .withClaim("username",username)
                .withClaim("user_id",String.valueOf(userId))
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(Duration.ofMillis(refresh_token_exp)))
                .sign(Algorithm.HMAC256(refresh_token));
    }
    public DecodedJWT decodeAccess(String token) throws TokenExpiredException{
        return JWT.require(Algorithm.HMAC256(access_token))
                .build()
                .verify(token);
    }

    public DecodedJWT decodeRefresh(String token) throws TokenExpiredException {
        return JWT.require(Algorithm.HMAC256(refresh_token)).build()
                .verify(token);
    }

    private RefreshTokenDTO toRefreshTokenDTO(RefreshToken refreshToken){
        return RefreshTokenDTO.builder()
                .id(refreshToken.getId())
                .expireRefreshToken(refreshToken.getExpireRefreshToken())
                .accessToken(refreshToken.getAccessToken())
                .refreshToken(refreshToken.getRefreshToken())
                .expireAccessToken(refreshToken.getExpireAccessToken())
                .build();
    }

}
