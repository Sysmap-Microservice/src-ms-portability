package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.domain.Portability;
import com.sysmap.srcmsportability.domain.User;
import com.sysmap.srcmsportability.domain.enums.CellPhoneOperator;
import com.sysmap.srcmsportability.domain.enums.StatusPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import com.sysmap.srcmsportability.framework.adapters.in.exceptions.PortabilityNotFound;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

public class PortabilityServiceImpl implements PortabilityService {

    private final PortabilityRepository portabilityRepository;

    private final UserPortabilityProducer userPortabilityProducer;

    public PortabilityServiceImpl(PortabilityRepository portabilityRepository, UserPortabilityProducer userPortabilityProducer) {
        this.portabilityRepository = portabilityRepository;
        this.userPortabilityProducer = userPortabilityProducer;
    }

    @Override
    public Portability createPortability(InputPortability inputPortability) {

        final ModelMapper modelMapper = new ModelMapper();
        final User user = modelMapper.map(inputPortability.getUser(), User.class);
        final String sourceInput = inputPortability.getPortability().getSource();
        final String targetInput = inputPortability.getPortability().getTarget();

        var response = Portability
                .builder()
                .source(CellPhoneOperator.valueOf(sourceInput.toUpperCase()))
                .target(CellPhoneOperator.valueOf(targetInput.toUpperCase()))
                .status(StatusPortability.PROCESSING_PORTABILITY)
                .user(user)
                .build();

        final Portability savedPortability = portabilityRepository.savePortability(response);
        userPortabilityProducer.send(savedPortability);
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


