package com.example.monyorms.config;

import com.example.monyorms.client.AuthServiceClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUserRoleFilter extends OncePerRequestFilter {

    private final AuthServiceClient authServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String userIdHeader = request.getHeader("X-Auth-User-Id");
        log.debug("X-Auth-User-Id header: {}", userIdHeader);

        if (userIdHeader != null) {
            try {
                Long userId = Long.parseLong(userIdHeader);
                String role = authServiceClient.getUserRole(userId);
                log.debug("User Role from AuthService: {}", role);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                log.debug("Authorities to set: {}", authorities);

                Authentication auth = new UsernamePasswordAuthenticationToken(userIdHeader, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.debug("Authentication set in SecurityContextHolder");

            } catch (Exception e) {
                log.error("Exception in AuthUserRoleFilter: {}", e.getMessage(), e);
            }
        } else {
            log.warn("X-Auth-User-Id header is missing");
        }

        filterChain.doFilter(request, response);
    }
}
