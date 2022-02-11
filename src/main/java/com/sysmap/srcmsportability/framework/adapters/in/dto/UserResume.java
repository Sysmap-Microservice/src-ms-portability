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
    @NotNull(message = "The line information cannot be null and must be filled in.")
    @OneToOne(cascade = CascadeType.PERSIST)
    private LineInformationResume line;

    @Valid
    @NotNull(message = "The address information cannot be null and must be filled in.")
    @OneToOne(cascade = CascadeType.PERSIST)
    private AddressResume address;

    @NotBlank(message = "The user name must be filled in and cannot be null or blank.")
    @Size(min = 3, max=80, message = "The name field must contain between 3 and 80 characters.")
    private String name;

    @CheckDateOfBirthValidator(message = "The date of birth must be in 'yyyy-mm-dd' format and must be in the past.")
    private String dateOfBirth;

    @NotBlank(message = "The document number must be filled in and cannot be null or blank.")
    @Size(min = 9, max = 11, message = "The document number must contain just 12 characters. ")
    private String documentNumber;
}
