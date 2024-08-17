package org.platform.service;

import org.platform.model.Account;
import org.platform.model.User;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createAccount(Account account);

    Account getAccount(UUID accountId);

    List<Account> getAccounts();

    Account updateAccount(UUID id, Account account);

    void deleteAccount(UUID accountId);
}
