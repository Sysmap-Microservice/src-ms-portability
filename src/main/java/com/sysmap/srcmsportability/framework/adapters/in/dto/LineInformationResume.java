package com.sysmap.srcmsportability.framework.adapters.in.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LineInformationResume {

    @NotBlank(message = "O NÚMERO DA OPERADORA ATUAL DEVE SER PREENCHIDO")
    @Size(min = 9, max = 11, message = "O NUMERO DA OPERADORA ATUAL DEVE CONTER ENTRE 9 E 11 DÍGITOS")
    private String number;
}
