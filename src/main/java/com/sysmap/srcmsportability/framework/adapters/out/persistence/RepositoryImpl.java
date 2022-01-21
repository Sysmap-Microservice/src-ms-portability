package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryImpl implements PortabilityRepository {

    @Autowired
    PostgreRepository postgreRepository;


}
