package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PortabilityService {

    public Portability createPortability(Portability portability);

    public ResponseEntity putStatusPortability(UUID portabilityId, StatusPortability status) throws ChangeSetPersister.NotFoundException;

}
