package org.platform.springJpa;

import org.platform.config.JwtUtil;
import org.platform.dto.login.LoginRequest;
import org.platform.dto.login.LoginResponse;
import org.platform.dto.register.RegisterRequest;
import org.platform.entity.UserEntity;
import org.platform.repository.UserRepository;
import org.platform.exceptions.AuthException;
import org.platform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSpringJpa implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Wrong email or password");
        }

        String token = jwtUtil.createToken(user);

        return new LoginResponse(token);
    }

    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new AuthException("User already exists");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(newUser);
    }
}
