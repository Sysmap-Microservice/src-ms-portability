package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import lombok.Data;

@Data
public class InputPutStatus {

    @NonNull
    private StatusPortability status;

}
