package com.example.auth.dto.security;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @Email
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter and one number")
    private String password;
    @NotNull
    private String first_name;
    @NotNull

    private String last_name;

}
