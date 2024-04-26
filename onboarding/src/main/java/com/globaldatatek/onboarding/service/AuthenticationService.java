package com.globaldatatek.onboarding.service;

import com.globaldatatek.onboarding.dto.JwtAuthenticationResponse;
import com.globaldatatek.onboarding.dto.SignInRequest;
import com.globaldatatek.onboarding.dto.SignUpRequest;
import com.globaldatatek.onboarding.model.Role;
import com.globaldatatek.onboarding.model.User;
import com.globaldatatek.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request) {
        var user = User
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getState())
                .zipcode(request.getZipcode())
                .role(Role.ROLE_USER)
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        var response = JwtAuthenticationResponse.builder().token(jwt).build();

        return  ResponseEntity.ok().body(response);
    }

    public ResponseEntity<JwtAuthenticationResponse> signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        var response = JwtAuthenticationResponse.builder().userId(user.getId()).token(jwt).build();

        return ResponseEntity.ok().body(response);
    }
}