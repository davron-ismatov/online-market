package com.example.feignclients.auth;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private Boolean isAccountExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
}
