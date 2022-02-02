package com.sysmap.srcmsportability.framework.adapters.out.topic.dto;

import lombok.*;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutputPortability {

    private String number;
    private String documentNumber;
    private PortabilityDTO portability;

}