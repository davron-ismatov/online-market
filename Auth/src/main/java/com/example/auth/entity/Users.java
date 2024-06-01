package com.example.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<Roles> roles;


    private Boolean isAccountExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;


    @PrePersist
    public void prePersist(){
        if (isAccountExpired==null && isAccountNonLocked==null && isEnabled==null && isCredentialsNonExpired==null){
             isAccountExpired = true;
             isAccountNonLocked = true;
             isCredentialsNonExpired = true;
             isEnabled = true;
        }
    }


}
