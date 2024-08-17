package org.platform.springJpa;

import org.platform.entity.AccountEntity;
import org.platform.entity.UserEntity;
import org.platform.exceptions.userexceptions.UserAlreadyExistException;
import org.platform.exceptions.userexceptions.UserApiException;
import org.platform.exceptions.userexceptions.UserNotFoundException;
import org.platform.model.User;
import org.platform.repository.AccountRepository;
import org.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TempUserSpringJpa {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    private final Map<String, User> tempUsers = new HashMap<>();


    public String createTempUser(User user) {
        String tempUserId = UUID.randomUUID().toString();
        tempUsers.put(tempUserId, user);
        return tempUserId;
    }

    public User completeUserCreation(String tempUserId, String password) {
        Optional<AccountEntity> accountEntity;
        User user = tempUsers.get(tempUserId);
        if (user == null) {
            throw new UserNotFoundException("User not found with id :" + tempUserId);
        }

        UserEntity userEntity;
        try { //Already exists email
            userEntity = userRepository.getByEmail(user.getEmail());
        } catch (Exception e) {
            throw new UserApiException("Problem while creating user");
        }
        if (userEntity != null) {
            throw new UserAlreadyExistException("User already exists with given email");
        }

        try { // Invalid account
            accountEntity = accountRepository.findById(user.getAccount_id());
        } catch (Exception e) {
            throw new UserApiException("Problem while creating user");
        }
        if (accountEntity.isEmpty()) {
            throw new UserNotFoundException("Account not found with given account id");
        }

        try {
            UserEntity newUserEntity = new UserEntity(user);
            newUserEntity.setAccountEntity(accountEntity.get());
            newUserEntity.setPassword(passwordEncoder.encode(password));
            userEntity = userRepository.save(newUserEntity);
        } catch (Exception e) {
            throw new UserApiException("Problem while creating user: " + e.getMessage());
        }


        tempUsers.remove(tempUserId);

        return userEntity.toUser();
    }
}