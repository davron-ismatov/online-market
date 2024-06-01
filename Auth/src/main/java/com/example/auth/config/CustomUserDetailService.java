package com.example.auth.config;

import com.example.auth.entity.Users;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return PrincipleUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .isCredentialsNonExpired(user.getIsCredentialsNonExpired())
                .isAccountExpired(user.getIsAccountExpired())
                .isEnabled(user.getIsEnabled())
                .isAccountNonLocked(user.getIsAccountNonLocked())
                .build();
    }
}
