package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.in.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserPostgreRepository extends CrudRepository<User, UUID> {

}
