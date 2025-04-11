package edu.icet.service;

import edu.icet.dto.LoginRequest;
import edu.icet.dto.RegisterRequest;

public interface UserService {
    void saveUser(RegisterRequest request);
    String authenticate(LoginRequest request);
}
