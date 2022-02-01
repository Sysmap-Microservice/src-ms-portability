package com.sysmap.srcmsportability.framework.adapters.out.topic.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.enums.CellPhoneOperator;
import lombok.AllArgsConstructor;
import lombok.ToString;


import java.util.UUID;

@ToString
@AllArgsConstructor
public class PortabilityDTO {

    private UUID portabilityId;
    private CellPhoneOperator source;
    private CellPhoneOperator target;
}
