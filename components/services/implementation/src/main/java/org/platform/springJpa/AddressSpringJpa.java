package org.platform.springJpa;


import org.platform.model.Address;
import org.platform.service.AddressService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AddressSpringJpa implements AddressService {


    @Override
    public Address getAddressByUserId(UUID userId) {
        return null;
    }
}
