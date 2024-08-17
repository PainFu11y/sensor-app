package org.platform.repository;

import org.platform.entity.AccountEntity;
import org.platform.entity.UserEntity;
import org.platform.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity getByAccountName(String accountName);
    AccountEntity getByAccountNameAndAccountIdNot(String accountName, UUID id);
}
