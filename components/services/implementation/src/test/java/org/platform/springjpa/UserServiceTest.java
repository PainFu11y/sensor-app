package org.platform.springjpa;


import org.platform.entity.AccountEntity;
import org.platform.entity.AddressEntity;
import org.platform.entity.UserEntity;
import org.platform.enums.AccountType;
import org.platform.exceptions.userexceptions.UserNotFoundException;
import org.platform.model.Address;
import org.platform.model.User;
import org.platform.repository.AccountRepository;
import org.platform.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.platform.springJpa.UserSpringJpa;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private UserSpringJpa userSpringJpa;

    private UUID accountId = UUID.randomUUID();

    private User userWithNonNullId = User.builder()
            .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            .name("TestUserName")
            .surname("TestUserSurname")
            .email("Test@test.test")
            .password("testPassword")
            .accountId(accountId)
            .address(new Address())
            .build();

    AccountEntity accountEntity = AccountEntity.builder()
            .accountId(accountId)
            .accountName("testAccount")
            .accountType(AccountType.CUSTOMER).build();

    UserEntity userEntity = UserEntity.builder()
            .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            .name("TestUserName")
            .surname("TestUserSurname")
            .email("Test@test.test")
            .password("testPassword")
            .accountEntity(accountEntity)
            .addressEntity(new AddressEntity())
            .build();

    @Test
    void testGetUserById() {

        given(userRepository.findById(any(UUID.class))).willReturn(Optional.of(userEntity));
        User user = userSpringJpa.getUserById(UUID.fromString("00000000-0000-0000-0000-000000000000"));

        assertEquals(user.getUserId(), userWithNonNullId.getUserId());
        assertEquals(user.getName(), userWithNonNullId.getName());
        assertEquals(user.getSurname(), userWithNonNullId.getSurname());

        verify(userRepository, times(1)).findById(UUID.fromString("00000000-0000-0000-0000-000000000000"));

        given(userRepository.findById(any(UUID.class))).willReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userSpringJpa.getUserById(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        });
    }


}