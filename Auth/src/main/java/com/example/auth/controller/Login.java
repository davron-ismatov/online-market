package com.example.auth.controller;

import com.example.auth.config.PrincipleUser;
import com.example.auth.dto.security.RefreshTokenDTO;
import com.example.auth.dto.security.UpdatePasswordDTO;
import com.example.auth.dto.security.UsersDTO;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class Login {
    private final AuthService service;

    @PostMapping
    public RefreshTokenDTO login(@RequestBody UsersDTO usersDTO){
        return service.login(usersDTO);
    }


    @GetMapping("/forgot-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String forgotPassword(@AuthenticationPrincipal PrincipleUser principleUser){
        return service.register(principleUser);
    }

    @GetMapping("/forgot-password/{token}/{code}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String verification( @PathVariable String code, @PathVariable String token) throws Exception {
        return service.passwordVerify(code,token);
    }

    @PostMapping("/update-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public RefreshTokenDTO changePassword(@RequestBody UpdatePasswordDTO dto,@AuthenticationPrincipal PrincipleUser principleUser) throws Exception {
        return service.updatePassword(dto,principleUser);
    }


}
