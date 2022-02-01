package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.User;

public interface UserPortabilityProducer {

    public void send(Portability portability);
}