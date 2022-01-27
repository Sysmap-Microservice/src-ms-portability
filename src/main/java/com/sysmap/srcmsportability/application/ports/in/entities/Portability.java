package com.sysmap.srcmsportability.application.ports.in.entities;

import com.sysmap.srcmsportability.application.ports.in.entities.enums.CellPhoneOperator;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_portability")
public class Portability {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "portability_id")
    private UUID portabilityId;

    private CellPhoneOperator source;
    private CellPhoneOperator target;
    private StatusPortability status;
}
