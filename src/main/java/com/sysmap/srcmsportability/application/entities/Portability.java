package com.sysmap.srcmsportability.application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_portability")
public class Portability {

    @Id
    @Column(name = "portability_id")
    private UUID portabilityId;
    private Operadora source;
    private Operadora target;
}
