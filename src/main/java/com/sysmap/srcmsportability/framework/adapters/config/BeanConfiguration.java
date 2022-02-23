package com.sysmap.srcmsportability.framework.adapters.config;

import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.application.service.PortabilityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SrcMsPortabilityApplication.class)
public class BeanConfiguration {

    @Bean
    PortabilityServiceImpl portabilityServiceImpl(PortabilityRepository portabilityRepository, UserPortabilityProducer userPortabilityProducer) {
        return new PortabilityServiceImpl(portabilityRepository, userPortabilityProducer);
    }
}
