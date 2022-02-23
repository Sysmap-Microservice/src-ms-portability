package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.framework.adapters.in.dto.*;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)

@SpringBootTest(
        classes = SrcMsPortabilityApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class PostPortabilityControllerTest {

    @Autowired
    private Validator validator;

    private InputPortability inputPortability;

    private LineInformationResume lineInformationResume;

    private AddressResume addressResume;

    private UserResume userResume;

    private PortabilityResume portabilityResume;

    @BeforeEach
    void setUp() {
        lineInformationResume = new LineInformationResume("987654321");
        addressResume =  new AddressResume("street", "number", "city", "country", "region");
        userResume = new UserResume(lineInformationResume, addressResume, "name", getStringPastDate(), "38894585212");
        portabilityResume  = new PortabilityResume("VIVO","CLARO");
        inputPortability = new InputPortability(portabilityResume, userResume);
    }

    @Test
    void verifyIfNoErrorsMessagesAreReturned() throws Exception {
        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertTrue(violations.isEmpty());
    }

    @Test
    void verifyIfReturnsErrorMessageWithNullUser() throws Exception {
        inputPortability.setUser(null);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("User data cannot be null.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithNullPortabilityResume() throws Exception {
        inputPortability.setPortability(null);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Portability data cannot be null.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithNullUserName() throws Exception {
        userResume.setName(null);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("The user name must be filled in and cannot be null or blank.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessagetWithEmptyDocumentNumber() throws Exception {
        userResume.setDocumentNumber(Strings.EMPTY);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final List<String> sViolations = getErrorMessageViolations(violations);
        assertTrue(sViolations.contains("The document number must be filled in and cannot be null or blank."));
    }

    @Test
    void verifyIfReturnsErrorMessageWithNullLineNumber() throws Exception{
        lineInformationResume.setNumber(null);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Current phone number must be filled in and cannot be null or blank.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithWrongSizeLineNumber() throws Exception{
        lineInformationResume.setNumber("123456");

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Current phone number must contain between 9 and 11 digits (without special characters).", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithNullAddress() throws Exception{
        userResume.setAddress(null);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("The address information cannot be null and must be filled in.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithWrongBirthOfDate() throws Exception{
        userResume.setDateOfBirth(getStringFutureDate());

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("The date of birth must be in 'yyyy-mm-dd' format and must be in the past.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithNullSourceOperator() throws Exception{
        portabilityResume.setSource(null);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Portability is only allowed from the phone operator Vivo. Null or blank values are not allowed.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithEmptyTargetOperator() throws Exception{
        portabilityResume.setTarget(Strings.EMPTY);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Null, empty or blank values are not allowed and only the following phone operators are allowed as a destination (target): Oi, Tim, Claro e Nextel.", violation.getMessage());
    }

    @Test
    void verifyIfReturnsErrorMessageWithWrongOperators() throws Exception{
        portabilityResume.setSource("OPERADORA_NAO_EXISTE");
        portabilityResume.setTarget("OPERADORA_NAO_EXISTE");

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final List<String> sViolations = getErrorMessageViolations(violations);
        assertTrue(sViolations.contains( "Portability is only allowed from the phone operator Vivo. Null or blank values are not allowed."));
        assertTrue(sViolations.contains("Null, empty or blank values are not allowed and only the following phone operators are allowed as a destination (target): Oi, Tim, Claro e Nextel."));
    }

    private static List<String> getErrorMessageViolations(Set<ConstraintViolation<InputPortability>> violations) {
        return violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
    }

    private static String getStringFutureDate() {
        return LocalDate.now().plusYears(1).toString();
    }

    private static String getStringPastDate() {
        return LocalDate.now().minusYears(1).toString();
    }
}