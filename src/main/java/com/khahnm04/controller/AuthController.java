package com.khahnm04.controller;

import com.khahnm04.exception.UserException;
import com.khahnm04.payload.dto.UserDTO;
import com.khahnm04.payload.response.AuthResponse;
import com.khahnm04.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
        @RequestBody UserDTO userDTO
    ) throws UserException {
        return ResponseEntity.ok(authService.signup(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDTO userDTO
    ) throws UserException {
        return ResponseEntity.ok(authService.login(userDTO));
    }

}
