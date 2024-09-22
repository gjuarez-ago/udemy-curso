package com.services.marketplace.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.services.marketplace.entity.Role;
import com.services.marketplace.entity.User;
import com.services.marketplace.jwt.JwtService;
import com.services.marketplace.model.JwtResponse;
import com.services.marketplace.model.LoginRequest;
import com.services.marketplace.model.RegisterRequest;
import com.services.marketplace.repository.RoleRepository;
import com.services.marketplace.repository.UserRepository;
import com.services.marketplace.service.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public JwtResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return JwtResponse.builder()
                .token(token)
                .build();

    }

    public JwtResponse register(RegisterRequest request) {
        Role userRole = findRoleByName(request.getRole()); // O el rol que desees asignar

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .firstSurname(request.getFirstSurname())
                .secondSurname(request.getSecondSurname())
                .role(userRole)
                .build();

        userRepository.save(user);

        return JwtResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    private Role findRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }
        return role;
    }

}
