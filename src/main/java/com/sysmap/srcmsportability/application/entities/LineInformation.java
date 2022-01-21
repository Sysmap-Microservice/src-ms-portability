package com.sysmap.srcmsportability.application.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_line")
public class LineInformation {

    @Id
    private UUID lineId;
    private String number;
}
