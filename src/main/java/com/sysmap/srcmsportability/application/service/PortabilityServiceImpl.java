package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.domain.entities.exceptions.PortabilityNotFound;

import java.util.Optional;
import java.util.UUID;

public class PortabilityServiceImpl implements PortabilityService {

    private final PortabilityRepository portabilityRepository;

    public PortabilityServiceImpl(PortabilityRepository portabilityRepository) {
        this.portabilityRepository = portabilityRepository;
    }

    @Override
    public Portability createPortability(Portability portability) {
        return this.portabilityRepository.savePortability(portability);
    }

    @Override
    public void putStatusPortability(UUID portabilityId, StatusPortability status) {

        Optional<Portability> optionalPortability = portabilityRepository.findPortabilityById(portabilityId);
        if(optionalPortability.isEmpty()) {
            throw new PortabilityNotFound("portability not found with id: " + portabilityId);
        }

        Portability portability = optionalPortability.get();
        portability.setStatus(status);
        portabilityRepository.savePortability(portability);
    }

}
