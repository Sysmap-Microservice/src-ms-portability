package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorSourceValidator;
import com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorTargetValidator;
import lombok.Data;

@Data
public class PortabilityResume {

    @CellPhoneOperatorSourceValidator(message =
            "Portability is only allowed from the phone operator Vivo. " +
            "Null or blank values are not allowed.")
    private String source;

    @CellPhoneOperatorTargetValidator(acceptedValues = {"TIM", "CLARO", "OI", "NEXTEL"},
            message = "Null, empty or blank values are not allowed and only the following phone operators " +
                    "are allowed as a destination (target): Oi, Tim, Claro e Nextel.")
    private String target;

}
