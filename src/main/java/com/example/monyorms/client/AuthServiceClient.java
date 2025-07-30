package com.example.monyorms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "${auth-service.url}")
public interface AuthServiceClient {

    @GetMapping("/users/{id}/exists")
    Boolean doesUserExist(@PathVariable Long id);

    @GetMapping("/api/auth/users/{id}/role")
    String getUserRole(@PathVariable("id") Long id);
}
