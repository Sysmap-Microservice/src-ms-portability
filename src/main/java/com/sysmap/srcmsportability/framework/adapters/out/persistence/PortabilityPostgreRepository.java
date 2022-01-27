package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PortabilityPostgreRepository extends CrudRepository<Portability, UUID> {

}
