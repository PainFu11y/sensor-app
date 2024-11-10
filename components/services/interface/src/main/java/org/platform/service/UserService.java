package org.platform.service;

import org.platform.model.Account;
import org.platform.model.User;
import org.platform.model.UserPassword;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId);

    User createUser(User user);

    void updatePassword(UUID userId, UserPassword password);

    List<User> getUsers(); 

    List<User> getUserByEmail(String email);

    void deleteUser(UUID userId);

    User updateUser(UUID id, User user);

    boolean updateCredential(String email, String password);

    List<User> search(String name, String surname);

}
