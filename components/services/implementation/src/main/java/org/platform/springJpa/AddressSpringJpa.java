package org.platform.springJpa;

import org.platform.entity.AddressEntity;
import org.platform.entity.UserEntity;
import org.platform.exceptions.ResourceNotFoundException;
import org.platform.model.Address;
import org.platform.repository.AddressRepository;
import org.platform.repository.UserRepository;
import org.platform.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressSpringJpa implements AddressService {


    @Override
    public Address getAddressByUserId(UUID userId) {
        return null;
    }
}
