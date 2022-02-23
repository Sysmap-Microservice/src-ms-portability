package com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions;

import com.sysmap.srcmsportability.domain.enums.CellPhoneOperator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CellPhoneOperatorSourceEqualsVivo implements ConstraintValidator<CellPhoneOperatorSourceValidator, String> {

    @Override
    public boolean isValid(String cellPhoneOperator, ConstraintValidatorContext constraintValidatorContext) {
        if (cellPhoneOperator == null || cellPhoneOperator.isEmpty() || cellPhoneOperator.isBlank())
            return false;
        return CellPhoneOperator.VIVO.toString().equals(cellPhoneOperator.toUpperCase());
    }

    @Override
    public void initialize(CellPhoneOperatorSourceValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
