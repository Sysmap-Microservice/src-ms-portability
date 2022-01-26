package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.domain.entities.Address;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository {

    Address saveAddress(Address address);
    Optional<Address> findAddressById(UUID addressId);
}
