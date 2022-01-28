package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.UserService;
import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.application.ports.out.UserRepository;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputUser;

import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(InputUser request) {
        var user = User.builder()
                .line(request.getLine())
                .address(request.getAddress())
                .name(request.getName())
                .dateOfBirth(request.getDateOfBirth())
                .documentNumber(request.getDocumentNumber())
                .portabilityId(request.getPortabilityId())
                .build();

        return this.userRepository.saveUser(user);
    }

    @Override
    public Optional<User> findUserById(UUID userId) {
        return userRepository.findUserById(userId);
    }

}
