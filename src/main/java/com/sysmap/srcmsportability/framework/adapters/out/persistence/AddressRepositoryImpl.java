package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.out.AddressRepository;
import com.sysmap.srcmsportability.domain.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    AddressPostgreRepository repository;

    @Override
    public Address saveAddress(Address address) {
        return repository.save(address);
    }

    @Override
    public Optional<Address> findAddressById(UUID addressId) {
        return repository.findById(addressId);
    }
}
