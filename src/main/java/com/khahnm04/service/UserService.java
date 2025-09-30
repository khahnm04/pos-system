package com.khahnm04.service;

import com.khahnm04.exception.UserException;
import com.khahnm04.model.User;
import java.util.List;

public interface UserService {

    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUsers();

}
