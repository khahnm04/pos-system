package com.khahnm04.payload.response;

import com.khahnm04.payload.dto.UserDTO;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDTO user;

}
