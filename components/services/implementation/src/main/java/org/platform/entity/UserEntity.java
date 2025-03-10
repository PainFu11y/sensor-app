package org.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.platform.constants.DatabaseConstants;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstants.USER_TABLE, schema = DatabaseConstants.SCHEMA)
public class UserEntity {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;



}
