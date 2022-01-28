package com.sysmap.srcmsportability.application.ports.in;

import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputUser;

public interface UserService {

    public User createUser(InputUser request);

}
