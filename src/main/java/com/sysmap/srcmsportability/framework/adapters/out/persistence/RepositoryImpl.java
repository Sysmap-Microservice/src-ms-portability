package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.out.Repository;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryImpl implements Repository {

    @Autowired
    PostgreRepository postgreRepository;

}
