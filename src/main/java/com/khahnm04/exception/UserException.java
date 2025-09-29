package com.khahnm04.exception;

import com.khahnm04.payload.dto.UserDTO;

public class UserException extends Throwable {

    public UserException(String message) {
        super(message);
    }

}
