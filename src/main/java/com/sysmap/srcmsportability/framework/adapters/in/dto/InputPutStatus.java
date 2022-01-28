package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class InputPutStatus {

    private StatusPortability status;

}
