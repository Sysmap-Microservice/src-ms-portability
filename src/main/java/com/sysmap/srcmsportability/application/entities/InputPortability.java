package com.sysmap.srcmsportability.application.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_input_portability")
public class InputPortability {

    @Id
    private UUID inputPortabilityId;

    @OneToOne
    private User user;

    @OneToOne
    private Portability portability;

}
