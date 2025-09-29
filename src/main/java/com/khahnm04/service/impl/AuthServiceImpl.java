package com.khahnm04.service.impl;

import com.khahnm04.configuration.JwtProvider;
import com.khahnm04.exception.UserException;
import com.khahnm04.model.User;
import com.khahnm04.payload.dto.UserDTO;
import com.khahnm04.payload.response.AuthResponse;
import com.khahnm04.repository.UserRepository;
import com.khahnm04.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDTO userDTO) throws UserException {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user != null) {
            throw new UserException("email id already registered !");
        }
        return null;
    }

    @Override
    public AuthResponse login(UserDTO userDTO) {
        return null;
    }

}
