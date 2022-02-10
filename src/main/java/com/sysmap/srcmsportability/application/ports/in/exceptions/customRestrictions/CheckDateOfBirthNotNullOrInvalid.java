package com.sysmap.srcmsportability.application.ports.in.exceptions.customRestrictions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckDateOfBirthNotNullOrInvalid implements ConstraintValidator<CheckDateOfBirthValidator, String> {

    private SimpleDateFormat dateTimeFormatter;

    @Override
    public void initialize(CheckDateOfBirthValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth == null || dateOfBirth.isEmpty() || dateOfBirth.isBlank())
            return false;

        Date convertedDate = convertToDate(dateOfBirth);

        if (convertedDate == null)
            return false;
        return convertedDate.before(Date.from(Instant.now()));
    }

    private static Date convertToDate(String dateOfBirth) {
        String regexDateFormat = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9])"; // dd/MM/yyyy
        Pattern pattern = Pattern.compile(regexDateFormat);
        Matcher matcher = pattern.matcher(dateOfBirth);

        if (!matcher.matches()) {
            return null;
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
