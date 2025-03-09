package org.platform.service;

import org.platform.dto.login.LoginRequest;
import org.platform.dto.register.RegisterRequest;
import org.platform.dto.login.LoginResponse;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
