package com.sysmap.srcmsportability.domain.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_user")
public class User {

    @Id
    private UUID userId;

    @OneToOne
    private LineInformation line;

    @OneToOne
    private Address address;

    private String name;
    private LocalDate dateOfBirth;
    private String documentNumber;
}
