package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.domain.entities.enums.StatusPortability;
import lombok.Data;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
public class InputPutStatus {

    private StatusPortability status;

}
