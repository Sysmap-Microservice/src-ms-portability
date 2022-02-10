package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CheckDateOfBirthValidator;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserResume {

    @Valid
    @NotNull(message = "A LINHA DEVE SER PREENCHIDA")
    @OneToOne(cascade = CascadeType.PERSIST)
    private LineInformationResume line;

    @Valid
    @NotNull(message = "O ENDEREÇO DEVE SER PREENCHIDO")
    @OneToOne(cascade = CascadeType.PERSIST)
    private AddressResume address;

    @NotBlank(message = "O NOME NÃO PODE FICAR VAZIO")
    @Size(min = 3, max=80, message = "NOME DEVE TER NO MIN=3 MAX=80 CARACTERES")
    private String name;

    @CheckDateOfBirthValidator(message = "A DATA DE ANIVERSARIO DEVE ESTAR NO PASSADO E NO PADRÃO DD/MM/YYYY")
    private String dateOfBirth;

    @NotBlank(message = "DOCUMENTO DEVE SER PREENCHIDO")
    @Size(min = 9, max = 11, message = "O NÚMERO DO DOCUMENTO DEVE CONTER ENTRE 9 E 11 DÍGITOS")
    private String documentNumber;
}
