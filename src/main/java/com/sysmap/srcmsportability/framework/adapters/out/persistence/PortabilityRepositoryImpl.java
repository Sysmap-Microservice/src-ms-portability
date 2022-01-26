package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.domain.entities.Portability;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PortabilityRepositoryImpl implements PortabilityRepository {

    @Override
    public Portability savePortability(Portability portability) {
        return null;
    }

    @Override
    public Optional<Portability> findPortabilityById(UUID portabilityId) {
        return Optional.empty();
    }
}
