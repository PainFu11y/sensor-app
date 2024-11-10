package org.platform.springJpa;

import org.platform.entity.AccountEntity;
import org.platform.exceptions.accountexceptions.AccountAlreadyExistException;
import org.platform.exceptions.accountexceptions.AccountApiException;
import org.platform.exceptions.accountexceptions.AccountBadRequestException;
import org.platform.exceptions.accountexceptions.AccountNotFoundException;
import org.platform.model.Account;
import org.platform.repository.AccountRepository;
import org.platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountSpringJpa implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        AccountEntity accountEntity;
        if(account.getAccountId() != null){
            throw new AccountBadRequestException("Account ID must be null");
        }
        try{
            accountEntity = accountRepository.getByAccountName(account.getAccountName());
        }catch (Exception e){
            throw new AccountApiException("Problem creating account");
        }
        if (accountEntity != null && accountEntity.getAccountType().equals(account.getAccountType())) {
            throw new AccountAlreadyExistException(
                    "Account already exists with name " + account.getAccountName() + " and type " + account.getAccountType());
        }

        accountEntity = AccountEntity.builder()
                .accountName(account.getAccountName())
                .accountType(account.getAccountType()).build();

        try{
            accountEntity = accountRepository.save(accountEntity);
        }catch (Exception e){
            throw new AccountApiException("Problem creating account");
        }
        return accountEntity.toAccount();
    }

    @Override
    public Account getAccount(UUID accountId) {
        AccountEntity accountEntity =  accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountEntity.toAccount();
    }

    @Override
    public List<Account> getAccounts() {
        List<AccountEntity> accountEntities;
        try{
            accountEntities = accountRepository.findAll();
        }catch (Exception e){
            throw new AccountApiException("Problem getting accounts");
        }
        return accountEntities
                .stream()
                .map(AccountEntity::toAccount)
                .toList();
    }

    @Override
    public Account updateAccount(UUID id, Account account) {
        Optional<AccountEntity> accountEntity;
        AccountEntity existingAccount;
        if(account.getAccountId() != null && !account.getAccountId().equals(id)){
            throw new AccountBadRequestException("Wrong account id");
        }
        try {
            accountEntity = accountRepository.findById(id);
        }catch (Exception e){
            throw new AccountApiException("Problem updating account");
        }
        if(accountEntity.isEmpty()){
            throw new AccountNotFoundException("Account not found");
        }
        AccountEntity byAccountNameAndAccountIdNot;
        try{
            byAccountNameAndAccountIdNot = accountRepository.getByAccountNameAndAccountIdNot(account.getAccountName(), account.getAccountId());
        }catch (Exception e){
            throw new AccountApiException("Problem updating account");
        }
        if(byAccountNameAndAccountIdNot != null &&
                byAccountNameAndAccountIdNot.getAccountType().equals(account.getAccountType())
        ){
            throw new AccountAlreadyExistException("Account already exists with given name and type");
        }

        existingAccount = accountEntity.get();
        existingAccount.setAccountName(account.getAccountName());
        existingAccount.setAccountType(account.getAccountType());
        try{
            existingAccount = accountRepository.save(existingAccount);
        }catch (Exception e){
            throw new AccountApiException("Problem updating account");
        }
        return  existingAccount.toAccount();

    }

    @Override
    public void deleteAccount(UUID accountId) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) return;

        try {
            accountRepository.deleteById(accountId);
        }catch (Exception e) {
           throw new AccountApiException("Problem deleting account");
        }
    }


}
