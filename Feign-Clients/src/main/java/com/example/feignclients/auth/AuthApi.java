package com.example.feignclients.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("AUTH")
public interface AuthApi {
    @GetMapping("/for-order/{id}")
    UserInfo getUserForOrder(@PathVariable(value = "id") String id);
}
