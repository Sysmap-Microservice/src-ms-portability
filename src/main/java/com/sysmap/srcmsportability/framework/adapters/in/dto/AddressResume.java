package com.sysmap.srcmsportability.framework.adapters.in.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressResume {

    @NotBlank(message = "Street must be filled in and cannot be null or blank.")
    private String street;
    @NotBlank(message = "Address number must be filled in and cannot be null or blank.")
    private String number;
    @NotBlank(message = "City must be filled in and cannot be null or blank.")
    private String city;
    @NotBlank(message = "Country must be filled in and cannot be null or blank.")
    private String country;
    @NotBlank(message = "State or Region must be filled in and cannot be null or blank.")
    private String stateOrRegion;
}
