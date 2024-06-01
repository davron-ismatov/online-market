package com.example.auth.entity;

import com.example.auth.enums.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles implements Serializable {
    @Id
    @Enumerated(EnumType.STRING)
    private RoleName role;
}
