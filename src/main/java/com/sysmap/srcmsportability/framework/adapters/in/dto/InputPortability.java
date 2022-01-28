package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import lombok.Data;

import java.util.UUID;

@Data
public class InputPortability {

    private Portability portability;
    private UUID userId;
}
