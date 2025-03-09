package org.platform.repository;

import org.platform.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
   UserEntity findByUsername(String username);
   boolean existsByUsername(String username);
}
