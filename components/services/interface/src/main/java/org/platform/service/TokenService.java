package org.platform.service;


import org.platform.dto.login.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String getToken(LoginRequest loginRequest);
}
