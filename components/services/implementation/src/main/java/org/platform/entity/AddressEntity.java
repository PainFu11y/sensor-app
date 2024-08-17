package org.platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.platform.constants.DatabaseConstants;
import org.platform.model.Address;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= DatabaseConstants.ADDRESS_TABLE, schema = DatabaseConstants.SCHEMA)
public class AddressEntity {
    @Id
    @Column(name="address_id")
    @GenericGenerator(name = "generator",strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID addressId;

    private String country;

    private String city;

    private String street;
    @OneToOne(mappedBy = "addressEntity")
    private UserEntity userEntity;

    public Address toAddress(){
        return new Address(addressId,country,city,street);
    }

    public AddressEntity(Address address){
        country = address.getCountry();
        city = address.getCity();
        street = address.getStreet();

    }


}
