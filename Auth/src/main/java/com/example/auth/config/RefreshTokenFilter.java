package com.example.auth.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> refreshToken = Optional.of(request.getHeader("davron"));
        if (refreshToken.isEmpty() || !refreshToken.get().startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        DecodedJWT decodedToken = jwtService.decodeRefresh(refreshToken.get().substring(7));
        filterChain.doFilter(request,response);
    }
}
