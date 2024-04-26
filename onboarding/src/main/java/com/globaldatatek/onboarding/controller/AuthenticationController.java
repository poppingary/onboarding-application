package com.globaldatatek.onboarding.controller;

import com.globaldatatek.onboarding.dto.SignInRequest;
import com.globaldatatek.onboarding.dto.JwtAuthenticationResponse;
import com.globaldatatek.onboarding.dto.SignUpRequest;
import com.globaldatatek.onboarding.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}