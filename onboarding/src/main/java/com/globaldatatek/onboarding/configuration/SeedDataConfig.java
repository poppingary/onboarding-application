package com.globaldatatek.onboarding.configuration;

import com.globaldatatek.onboarding.model.Role;
import com.globaldatatek.onboarding.model.User;
import com.globaldatatek.onboarding.repository.UserRepository;
import com.globaldatatek.onboarding.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = User
                    .builder()
                    .firstName("Gary")
                    .lastName("Lin")
                    .email("gl2312@nyu.edu")
                    .password(passwordEncoder.encode("admin"))
                    .phoneNumber("3472176082")
                    .dateOfBirth("08/09/1993")
                    .street("33 w 315 st")
                    .city("New York")
                    .state("NY")
                    .zipcode("10001")
                    .role(Role.ROLE_ADMIN)
                    .createdBy("Gary")
                    .updatedBy("Gary")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}