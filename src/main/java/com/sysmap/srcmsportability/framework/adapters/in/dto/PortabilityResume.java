package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.enums.CellPhoneOperator;
import lombok.Data;

@Data
public class PortabilityResume {

    private CellPhoneOperator source;
    private CellPhoneOperator target;

}
