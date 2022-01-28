package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.UserService;
import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.application.ports.out.UserRepository;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputUser;

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
                .build();

        return this.userRepository.saveUser(user);
    }

}
