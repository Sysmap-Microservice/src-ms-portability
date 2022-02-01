package com.sysmap.srcmsportability.framework.adapters.out.topic.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import lombok.ToString;

@ToString
public class OutputPortability {

    private String number;
    private String documentNumber;
    private PortabilityDTO portability;

    public OutputPortability(Portability portability){
        this.number = portability.getUser().getLine().getNumber();
        this.documentNumber = portability.getUser().getDocumentNumber();
        this.portability = new PortabilityDTO(
                portability.getPortabilityId(),portability.getSource(), portability.getTarget());
    }
}