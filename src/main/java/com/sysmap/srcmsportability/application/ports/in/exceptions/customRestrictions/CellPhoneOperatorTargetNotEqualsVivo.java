package com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CellPhoneOperatorTargetNotEqualsVivo implements ConstraintValidator<CellPhoneOperatorTargetValidator, String> {

    private List<String> acceptedValues;

    @Override
    public boolean isValid(String cellPhoneOperator, ConstraintValidatorContext constraintValidatorContext) {
        if (cellPhoneOperator == null || cellPhoneOperator.isEmpty() || cellPhoneOperator.isBlank())
            return false;
        return acceptedValues.contains(cellPhoneOperator.toUpperCase());
    }

    @Override
    public void initialize(CellPhoneOperatorTargetValidator targetValidator) {
        this.acceptedValues = List.of(targetValidator.acceptedValues());
    }
}
