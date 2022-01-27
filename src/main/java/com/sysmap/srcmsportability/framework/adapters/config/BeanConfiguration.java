package com.sysmap.srcmsportability.framework.adapters.config;

import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserRepository;
import com.sysmap.srcmsportability.application.service.PortabilityServiceImpl;
import com.sysmap.srcmsportability.application.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SrcMsPortabilityApplication.class)
public class BeanConfiguration {

    @Bean
    PortabilityServiceImpl portabilityServiceImpl(PortabilityRepository portabilityRepository) {
        return new PortabilityServiceImpl(portabilityRepository);
    }

    @Bean
    UserServiceImpl userServiceImpl(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
