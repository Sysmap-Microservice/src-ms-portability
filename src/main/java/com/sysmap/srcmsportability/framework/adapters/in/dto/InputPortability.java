package com.sysmap.srcmsportability.framework.adapters.in.dto;

import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.CellPhoneOperator;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import lombok.Data;

@Data
public class InputPortability {

    private CellPhoneOperator source;

    private CellPhoneOperator target;

    private StatusPortability status;

    private User user;
}
