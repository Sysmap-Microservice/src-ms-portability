package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;

public interface UserPortabilityProducer {

    public void send(Portability portability);
}