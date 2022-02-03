package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.domain.Portability;

public interface UserPortabilityProducer {

    void send(Portability portability);

}
