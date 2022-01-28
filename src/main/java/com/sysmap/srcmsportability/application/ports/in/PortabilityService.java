package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.domain.entities.Portability;
import com.sysmap.srcmsportability.domain.entities.enums.StatusPortability;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PortabilityService {

    Portability createPortability(Portability portability);
    void putStatusPortability(UUID portabilityId, StatusPortability status);

}
