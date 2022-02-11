package com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckDateOfBirthNotNullOrInvalid implements ConstraintValidator<CheckDateOfBirthValidator, String> {

    @Override
    public void initialize(CheckDateOfBirthValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth == null || dateOfBirth.isEmpty() || dateOfBirth.isBlank())
            return false;

        final LocalDate convertedDate = convertToDate(dateOfBirth);

        if (convertedDate == null)
            return false;
        return convertedDate.isBefore(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
    }

    private static LocalDate convertToDate(String dateOfBirth) {
        final String regexDateFormat = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"; // yyyy-MM-dd
        final Pattern pattern = Pattern.compile(regexDateFormat);
        final Matcher matcher = pattern.matcher(dateOfBirth);

        if (!matcher.matches()) {
            return null;
        }
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(dateOfBirth);
        } catch (DateTimeParseException e) {
        }
        return localDate;
    }
}
