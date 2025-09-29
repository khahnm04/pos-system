package com.khahnm04.payload.dto;

import com.khahnm04.domain.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private UserRole role;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime lastLogin;

}
