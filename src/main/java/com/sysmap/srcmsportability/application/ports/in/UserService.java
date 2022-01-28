package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputUser;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(InputUser request);
    Optional<User> findUserById(UUID userId);

}
