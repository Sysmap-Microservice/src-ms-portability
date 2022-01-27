package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.Address;
import com.sysmap.srcmsportability.application.ports.in.entities.LineInformation;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.User;
import lombok.Data;

@Data
public class InputPortability {

    private User user;
    private Portability portability;
    private Address address;
    private LineInformation lineInformation;
}
