package com.sysmap.srcmsportability.application.ports.in.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_user")
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(cascade=CascadeType.PERSIST)
    private LineInformation line;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;

    private String name;

    private LocalDate dateOfBirth;

    private String documentNumber;

    private UUID portabilityId;
}
