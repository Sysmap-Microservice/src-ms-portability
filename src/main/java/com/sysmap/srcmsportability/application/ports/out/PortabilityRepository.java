package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.domain.entities.Portability;

import java.util.Optional;
import java.util.UUID;

public interface PortabilityRepository {

    Portability savePortability(Portability portability);
    Optional<Portability> findPortabilityById(UUID portabilityId);
}
