package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.domain.Portability;
import com.sysmap.srcmsportability.domain.enums.StatusPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;

import java.util.UUID;

public interface PortabilityService {

    Portability createPortability(InputPortability portability);
    void putStatusPortability(UUID portabilityId, StatusPortability status);

}
