package com.sysmap.srcmsportability.framework.adapters.in.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LineInformationResume {

    @NotBlank(message = "Current phone number must be filled in and cannot be null or blank.")
    @Size(min = 9, max = 11, message = "Current phone number must contain between 9 and 11 digits (without special characters).")
    private String number;
}
