package com.example.auth.service;

import com.example.auth.entity.Users;
import com.example.auth.repository.UserRepository;
import com.example.feignclients.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users",key = "#id")
    public Users getUserById(Long id){
        log.info("Working with Database");
        return userRepository.findById(id).orElse(null);
    }

    public List<UserInfo> getUserForOrder(){
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(this::toUserWithoutAttachmentsDTO).toList();
    }

    private UserInfo toUserWithoutAttachmentsDTO(Users users){
        return UserInfo.builder()
                .first_name(users.getFirst_name())
                .isAccountExpired(users.getIsAccountExpired())
                .last_name(users.getLast_name())
                .isAccountNonLocked(users.getIsAccountNonLocked())
                .isCredentialsNonExpired(users.getIsCredentialsNonExpired())
                .isEnabled(users.getIsEnabled())
                .password(users.getPassword())
                .username(users.getUsername())
                .id(users.getId())
                .build();
    }

    public UserInfo getUserForOrder(@PathVariable Long id){
        return toUserWithoutAttachmentsDTO(userRepository.findById(id).orElseThrow());
    }
}
