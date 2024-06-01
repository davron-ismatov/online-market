package com.example.auth.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.auth.entity.RefreshToken;
import com.example.auth.entity.Users;
import com.example.auth.repository.RefreshTokenRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService authService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Authorization");
        String refToken = request.getHeader("davron");
        if (auth == null || !auth.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String jwt;
        jwt = auth.substring(7);
        DecodedJWT decode;
        try {
            decode = jwtService.decodeAccess(jwt);
            Users userById = authService.getUserById(Long.valueOf(decode.getSubject()));

            PrincipleUser user = toDTO(userById);

            SecurityContextHolder.getContext().setAuthentication(new UserTokenAuth(user));
            filterChain.doFilter(request,response);

        }catch (TokenExpiredException ex){
            refToken = refToken.substring(7);

            DecodedJWT decodedJWT = jwtService.decodeRefresh(refToken);
            Users byUsername = userRepository.findByUsername(decodedJWT.getSubject())
                    .orElseThrow();
            String newToken = jwtService.generateAccessToken(byUsername.getId(), byUsername.getUsername(), byUsername.getRoles());
            RefreshToken refreshToken = refreshTokenRepository.getReferenceById(UUID.fromString(decodedJWT.getSubject()));
            refreshToken.setAccessToken(newToken);
            refreshTokenRepository.save(refreshToken);
            PrincipleUser user = toDTO(byUsername);
            SecurityContextHolder.getContext().setAuthentication(new UserTokenAuth(user));
            filterChain.doFilter(request,response);

        }


    }



    private PrincipleUser toDTO(Users userById){
        return  PrincipleUser.builder()
                .id(userById.getId())
                .username(userById.getUsername())
                .roles(userById.getRoles())
                .isAccountExpired(userById.getIsAccountExpired())
                .isAccountNonLocked(userById.getIsAccountNonLocked())
                .isEnabled(userById.getIsEnabled())
                .isCredentialsNonExpired(userById.getIsCredentialsNonExpired())
                .build();
    }

}
