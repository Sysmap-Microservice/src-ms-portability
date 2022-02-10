package com.sysmap.srcmsportability.framework.adapters.in.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressResume {

    @NotBlank(message = "A RUA DEVE SER PREENCHIDA")
    private String street;
    @NotBlank(message = "O NÚMERO DO ENDEREÇO DEVE SER PREENCHIDO")
    private String number;
    @NotBlank(message = "A CIDADE DEVE SER PREENCHIDA")
    private String city;
    @NotBlank(message = "O PAÍS DEVE SER PREENCHIDO")
    private String country;
    @NotBlank(message = "O ESTADO DEVE SER PREENCHIDO")
    private String stateOrRegion;
}
