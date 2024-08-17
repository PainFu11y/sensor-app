package org.platform.model;

import lombok.Getter;
import lombok.Setter;
import org.platform.enums.AccountType;

import java.util.UUID;

@Getter
@Setter
public class Account {
    private UUID accountId;
    private String accountName;
    private AccountType accountType;
}
