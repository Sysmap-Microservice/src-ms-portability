package com.sysmap.srcmsportability.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_address")
public class Address {

    @Id
    private UUID addressId;
    private String street;
    private String number;
    private String city;
    private String country;
    private String stateOrRegion;
}
