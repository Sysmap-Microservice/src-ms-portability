package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;

public interface UserPortabilityProducer {

    void send(Portability portability);

}
