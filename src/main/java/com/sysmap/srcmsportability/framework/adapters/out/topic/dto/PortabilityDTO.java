package com.sysmap.srcmsportability.framework.adapters.out.topic.dto;

import com.sysmap.srcmsportability.domain.enums.CellPhoneOperator;
import lombok.*;

import java.util.UUID;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortabilityDTO {

    private UUID portabilityId;
    private CellPhoneOperator source;
    private CellPhoneOperator target;
}
