package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.domain.entities.Portability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortabilityServiceImpl implements PortabilityService {

    @Autowired
    private PortabilityRepository portabilityRepository;

    public Portability createPortability(Portability portability) {
        return portabilityRepository.savePortability(portability);
    }
}
