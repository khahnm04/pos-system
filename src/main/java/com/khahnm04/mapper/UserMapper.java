package com.khahnm04.mapper;

import com.khahnm04.model.User;
import com.khahnm04.payload.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setCreateAt(user.getCreateAt());
        userDTO.setUpdateAt(user.getUpdateAt());
        userDTO.setLastLogin(user.getLastLogin());
        return userDTO;
    }

}
