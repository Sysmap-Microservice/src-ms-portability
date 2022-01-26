package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.domain.entities.Portability;

public class PortabilityServiceImpl implements PortabilityService {

    private final PortabilityRepository portabilityRepository;

    public PortabilityServiceImpl(PortabilityRepository portabilityRepository) {
        this.portabilityRepository = portabilityRepository;
    }

    public Portability createPortability(Portability portability) {
        return portabilityRepository.savePortability(portability);
    }
}
