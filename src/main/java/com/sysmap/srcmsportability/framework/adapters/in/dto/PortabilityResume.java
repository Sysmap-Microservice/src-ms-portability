package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorSourceValidator;
import com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorTargetValidator;
import lombok.Data;

@Data
public class PortabilityResume {

    @CellPhoneOperatorSourceValidator(message = "SOMENTE É PERMITIDA A PORTABILIDADE À PARTIR DA OPERADORA VIVO. "
            + "VALORES NULOS OU EM BRANCO NÃO SÃO PERMITIDOS!")
    private String source;

    @CellPhoneOperatorTargetValidator(acceptedValues = {"TIM", "CLARO", "OI", "NEXTEL"},
            message = "SOMENTE É PERMITIDA A PORTABILIDADE PARA AS OPERADORAS OI, TIM, CLARO E NEXTEL! VALORES NULOS OU EM BRANCO NÃO SÃO PERMITIDOS!")
    private String target;

}
