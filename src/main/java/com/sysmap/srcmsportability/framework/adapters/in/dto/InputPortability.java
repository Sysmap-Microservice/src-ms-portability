package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.domain.entities.Portability;
import com.sysmap.srcmsportability.domain.entities.User;
import lombok.Data;

@Data
public class InputPortability {

    private User user;
    private Portability portability;
}
