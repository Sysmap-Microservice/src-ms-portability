package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.domain.entities.Portability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PortabilityRepositoryImpl implements PortabilityRepository {

    @Autowired
    PortabilityPostgreRepository repository;

    @Override
    public Portability savePortability(Portability portability) {
        return repository.save(portability);
    }

    @Override
    public Optional<Portability> findPortabilityById(UUID portabilityId) {
        return repository.findById(portabilityId);
    }
}
