package com.khahnm04.service;

import com.khahnm04.exception.UserException;
import com.khahnm04.payload.dto.UserDTO;
import com.khahnm04.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDTO userDTO) throws UserException;
    AuthResponse login(UserDTO userDTO);

}
