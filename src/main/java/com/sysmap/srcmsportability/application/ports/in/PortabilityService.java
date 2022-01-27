package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.domain.entities.Portability;
import com.sysmap.srcmsportability.domain.entities.User;
import com.sysmap.srcmsportability.domain.entities.enums.StatusPortability;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PortabilityService {

    public Portability createPortability(Portability portability, User user);

    public ResponseEntity putStatusPortability(UUID portabilityId, StatusPortability status) throws ChangeSetPersister.NotFoundException;

}
