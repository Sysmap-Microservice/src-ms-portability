package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User saveUser(User user);
    Optional<User> findUserById(UUID userId);

}
