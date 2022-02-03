package com.sysmap.srcmsportability.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "address_id")
    private UUID addressId;
    private String street;
    private String number;
    private String city;
    private String country;
    private String stateOrRegion;
}
