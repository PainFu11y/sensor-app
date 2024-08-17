package org.platform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.platform.constants.DatabaseConstants;
import org.platform.enums.AccountType;
import org.platform.model.Account;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = DatabaseConstants.ACCOUNT_TABLE, schema = DatabaseConstants.SCHEMA)
public class AccountEntity {
    @Id
    @Column(name = "account_id")
    @GenericGenerator(name = "generator",strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID accountId;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "accountEntity")
    private List<UserEntity> userEntity;

    public Account toAccount(){
        Account account = new Account();
        account.setAccountId(accountId);
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        return account;
    }
}
