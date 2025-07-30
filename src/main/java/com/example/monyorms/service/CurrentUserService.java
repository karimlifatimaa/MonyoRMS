package com.example.monyorms.service;

import com.example.monyorms.client.AuthServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("currentUserService")
@RequiredArgsConstructor
public class CurrentUserService {

    private final AuthServiceClient authServiceClient;

    public boolean hasRole(Long userId, String role) {
        String userRole = authServiceClient.getUserRole(userId);
        return userRole.equalsIgnoreCase(role);
    }
}
