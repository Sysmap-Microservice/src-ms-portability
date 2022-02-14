package com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CellPhoneOperatorSourceEqualsVivo.class})
@Documented
public @interface CellPhoneOperatorSourceValidator {

    String message() default "{com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorSource.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {CellPhoneOperatorSourceEqualsVivo.class})
    @Documented
    @interface List {
        CellPhoneOperatorSourceValidator value();
    }
}
