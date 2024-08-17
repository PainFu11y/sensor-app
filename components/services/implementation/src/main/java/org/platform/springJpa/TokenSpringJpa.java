package org.platform.springJpa;

import org.platform.config.JwtUtil;
import org.platform.entity.UserEntity;
import org.platform.exceptions.userexceptions.UserApiException;
import org.platform.exceptions.userexceptions.UserBadRequestException;
import org.platform.model.LoginRequest;
import org.platform.repository.UserRepository;
import org.platform.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenSpringJpa implements TokenService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String getToken(LoginRequest loginRequest) {
        String token = null;

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            String email = authenticate.getName();
            UserEntity userEntity = userRepository.getByEmail(email);
            userEntity.setPassword("");
            token = jwtUtil.createToken(userEntity);
        } catch (BadCredentialsException e) {
            throw new UserBadRequestException("Invalid username or password");
        } catch (Exception e) {
            throw new UserApiException("problem during getting token");
        }

        return token;
    }
}
