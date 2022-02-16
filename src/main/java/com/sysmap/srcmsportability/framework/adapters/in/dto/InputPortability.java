package com.sysmap.srcmsportability.framework.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class InputPortability {

    @Valid
    @NotNull(message = "Portability data cannot be null.")
    private PortabilityResume portability;

    @Valid
    @NotNull(message = "User data cannot be null.")
    private UserResume user;

}
