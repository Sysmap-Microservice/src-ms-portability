package com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CellPhoneOperatorTargetNotEqualsVivo.class})
@Documented
public @interface CellPhoneOperatorTargetValidator {

    String[] acceptedValues() default "{com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorTargetValidator.acceptedValues}";

    String message() default "{com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions.CellPhoneOperatorTargetValidator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        CellPhoneOperatorTargetValidator value();
    }
}
