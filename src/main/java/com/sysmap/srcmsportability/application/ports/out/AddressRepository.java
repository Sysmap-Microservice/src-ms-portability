package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.application.ports.in.entities.Address;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository {

    Address saveAddress(Address address);
    Optional<Address> findAddressById(UUID addressId);
}
