package org.platform.config;

import lombok.SneakyThrows;
import org.platform.entity.UserEntity;
import org.platform.exceptions.userexceptions.UserApiException;
import org.platform.exceptions.userexceptions.UserNotFoundException;
import org.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = null;
        try {
            userEntity = userRepository.getByEmail(username);
        } catch (Exception ex) {
            throw new UserApiException("Problem during authorization process");
        }
        if (userEntity == null) {
            throw new UserNotFoundException("Wrong email or password");
        }

        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(new ArrayList<>())
                .build();

    }
}
