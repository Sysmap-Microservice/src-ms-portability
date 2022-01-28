package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.Address;
import com.sysmap.srcmsportability.application.ports.in.entities.LineInformation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InputUser {

    private String name;

    private LineInformation line;

    private Address address;

    private LocalDate dateOfBirth;

    private String documentNumber;

}
