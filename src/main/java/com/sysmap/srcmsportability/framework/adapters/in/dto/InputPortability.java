package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.domain.User;
import lombok.Data;

@Data
public class InputPortability {

    private PortabilityResume portability;

    private User user;

}
