package com.mohamedbob.estate.service;

import com.mohamedbob.estate.config.JwtProvider;
import com.mohamedbob.estate.dto.AuthResponseDTO;
import com.mohamedbob.estate.model.LoginRequest;
import com.mohamedbob.estate.model.RegisterRequest;
import com.mohamedbob.estate.repository.RoleRepository;
import com.mohamedbob.estate.repository.UserRepository;
import com.mohamedbob.estate.model.Role;
import com.mohamedbob.estate.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtProvider = jwtProvider;
    }

    // REGISTER
    public AuthResponseDTO register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("Name already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        // Assign default role
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_USER");
                    return roleRepository.save(role);
                });
        user.setRoles(new HashSet<>(Collections.singleton(userRole)));

        User savedUser = userRepository.save(user);

        String token = generateToken(savedUser);

        // Return the roles
        List<String> roles = savedUser.getRoles().stream()
                .map(Role::getName)
                .toList();

        return new AuthResponseDTO(savedUser.getId(), token, savedUser.getEmail(), savedUser.getName(), roles);
    }

    // LOGIN
    public AuthResponseDTO login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

            String token = generateToken(user);

            List<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .toList();

            return new AuthResponseDTO(user.getId(), token, user.getEmail(), user.getName(), roles);
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Invalid email or password");
        }
    }

    // GENERATE JWT TOKEN
    private String generateToken(User user) {
        return jwtProvider.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName()))
                                .toList()
                )
        );
    }
}
