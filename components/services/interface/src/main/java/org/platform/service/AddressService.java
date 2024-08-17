package org.platform.service;


import org.platform.model.Address;

import java.util.UUID;

public interface AddressService {
    Address getAddressByUserId(UUID userId);
}
