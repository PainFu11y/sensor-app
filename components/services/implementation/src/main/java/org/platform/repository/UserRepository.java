package org.platform.repository;

import org.platform.entity.UserEntity;
import org.platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity getByEmail(String email);
    UserEntity getByEmailAndUserIdNot(String email, UUID id);
}
