package com.example.auth.component;

import com.example.auth.entity.Roles;
import com.example.auth.enums.RoleName;
import com.example.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final RoleRepository repository;

    @Override
    public void run(String... args) throws Exception {
        log.info("DataLoader is started working");
        if (repository.count()==0){
            for (RoleName roleName:RoleName.values()) {
                repository.save(new Roles(roleName));
            }
        }
    }



}
