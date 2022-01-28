package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;

import java.util.UUID;

public interface PortabilityService {

    Portability createPortability(Portability portability);
    void putStatusPortability(UUID portabilityId, StatusPortability status);

}
