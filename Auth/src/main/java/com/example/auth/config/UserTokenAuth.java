package com.example.auth.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserTokenAuth extends AbstractAuthenticationToken {
    private final PrincipleUser principleUser;
    public UserTokenAuth(PrincipleUser principleUser) {
        super(principleUser.getAuthorities());
        this.principleUser = principleUser;
        setAuthenticated(principleUser.isAccountNonExpired()&&principleUser.isAccountNonLocked()&&principleUser.isCredentialsNonExpired()&&principleUser.isEnabled())   ;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principleUser;
    }
}
