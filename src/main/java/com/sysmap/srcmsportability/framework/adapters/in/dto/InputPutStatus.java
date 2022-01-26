package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.domain.entities.enums.StatusPortability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputPutStatus {

    private StatusPortability status;

}
