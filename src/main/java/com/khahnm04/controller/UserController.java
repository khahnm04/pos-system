package com.khahnm04.controller;

import com.khahnm04.exception.UserException;
import com.khahnm04.mapper.UserMapper;
import com.khahnm04.model.User;
import com.khahnm04.payload.dto.UserDTO;
import com.khahnm04.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(
        @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
        @RequestHeader("Authorization") String jwt,
        @PathVariable Long id
    ) throws UserException, Exception {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

}
