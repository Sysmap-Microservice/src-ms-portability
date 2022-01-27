package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity putStatusPortability(UUID portabilityId, StatusPortability status) throws ChangeSetPersister.NotFoundException {

        Optional<Portability> optionalPortability = portabilityRepository.findPortabilityById(portabilityId);
        if(!optionalPortability.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Portability portability = optionalPortability.get();
        portability.setStatus(status);
        portabilityRepository.savePortability(portability);

        return ResponseEntity.ok(portability);
    }

}
