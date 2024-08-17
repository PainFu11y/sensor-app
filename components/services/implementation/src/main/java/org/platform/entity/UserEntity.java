package org.platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.platform.constants.DatabaseConstants;
import org.platform.model.User;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = DatabaseConstants.USER_TABLE, schema = DatabaseConstants.SCHEMA)
public class UserEntity {
    @Id
    @Column(name="user_id")
    @GenericGenerator(name = "generator",strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID userId;
    @Column(name="first_name")
    private String name;
    @Column(name="last_name")
    private String surname;

    private String email;

    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @RestResource(path = "userAccount", rel = "accountEntity")
    AccountEntity accountEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @RestResource(path ="userAddress", rel = "addressEntity")
    private AddressEntity addressEntity;

    public User toUser(){
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setAccount_id(accountEntity.getAccountId());
        user.setAddress(addressEntity.toAddress());
        return user;
    }

    public UserEntity(User user){
        userId = user.getUserId();
        name = user.getName();
        surname = user.getSurname();
        email = user.getEmail();
        addressEntity =  new AddressEntity(user.getAddress());
    }

}
