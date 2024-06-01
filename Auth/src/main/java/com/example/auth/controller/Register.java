package com.example.auth.controller;

import com.example.auth.dto.security.RefreshTokenDTO;
import com.example.auth.dto.security.RegisterDTO;
import com.example.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class Register {
    private final AuthService service;
    @PostMapping
    public String register(@RequestBody @Valid RegisterDTO users){
        return service.register(users);
    }


    @GetMapping("/verify/{token}/{code}")
    public RefreshTokenDTO verify(@PathVariable String token, @PathVariable String code) throws Exception {
            return service.verify(code,token);
    }
}
