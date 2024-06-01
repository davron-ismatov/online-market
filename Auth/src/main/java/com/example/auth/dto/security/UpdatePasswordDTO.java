package com.example.auth.dto.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdatePasswordDTO {
    private String newPassword;
}
