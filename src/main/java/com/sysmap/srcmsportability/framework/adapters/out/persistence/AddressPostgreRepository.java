package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.domain.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressPostgreRepository extends CrudRepository<Address, UUID> {
}
