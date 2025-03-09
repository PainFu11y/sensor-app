package org.platform.springJpa;

import org.platform.config.JwtUtil;
import org.platform.dto.login.LoginRequest;
import org.platform.entity.UserEntity;
import org.platform.exceptions.AuthException;
import org.platform.repository.UserRepository;
import org.platform.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

            String name = authenticate.getName();
            UserEntity userEntity = userRepository.findByUsername(name);
            userEntity.setPassword("");
            token = jwtUtil.createToken(userEntity);
        }catch (Exception e){
            throw new AuthException(e.getMessage());
        }
//        catch (RuntimeException e) {
//            throw new AuthException("Invalid  username or password");
//        } catch (Exception e) {
//            throw new SensorApiException("problem during getting token");
//        }

        return token;
    }


}
