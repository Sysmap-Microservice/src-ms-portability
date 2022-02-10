package com.sysmap.srcmsportability.framework.adapters.in.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class InputPortability {

    @Valid
    @NotNull(message = "A PORTABILIDADE NÃO PODE SER NULA")
    private PortabilityResume portability;

    @Valid
    @NotNull(message = "O USUÁRIO NÃO PODE SER NULO")
    private UserResume user;

}
