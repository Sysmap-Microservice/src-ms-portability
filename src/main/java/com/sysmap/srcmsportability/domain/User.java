package com.sysmap.srcmsportability.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private LineInformation line;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    private String name;

    private LocalDate dateOfBirth;

    private String documentNumber;

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }
}
