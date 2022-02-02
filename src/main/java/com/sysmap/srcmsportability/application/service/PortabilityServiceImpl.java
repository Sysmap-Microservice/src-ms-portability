package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.domain.entities.exceptions.PortabilityNotFound;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;

import java.util.Optional;
import java.util.UUID;

public class PortabilityServiceImpl implements PortabilityService {

    private final PortabilityRepository portabilityRepository;

    private final UserPortabilityProducer userPortabilityProducer;

    public PortabilityServiceImpl(PortabilityRepository portabilityRepository,UserPortabilityProducer userPortabilityProducer) {
        this.portabilityRepository = portabilityRepository;
        this.userPortabilityProducer = userPortabilityProducer;
    }

    @Override
    public Portability createPortability(InputPortability portability) {

        var response = Portability.builder()
                .source(portability.getSource())
                .status(portability.getStatus())
                .target(portability.getTarget())
                .user(portability.getUser())
                .build();
        final Portability savedPortability = portabilityRepository.savePortability(response);
        userPortabilityProducer.send( savedPortability );
        return savedPortability;
    }

    @Override
    public void putStatusPortability(UUID portabilityId, StatusPortability status) {

        Optional<Portability> optionalPortability = portabilityRepository.findPortabilityById(portabilityId);
        if (optionalPortability.isEmpty()) {
            throw new PortabilityNotFound("portability not found with id: " + portabilityId);
        }

        Portability portability = optionalPortability.get();
        portability.setStatus(status);
        portabilityRepository.savePortability(portability);
    }

}


