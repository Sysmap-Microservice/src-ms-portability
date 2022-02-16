package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.framework.adapters.in.dto.*;
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

    @Test
    public void verifyIfNoErrorsMessagesAreReturned() throws Exception {
        final LineInformationResume lineInformation = getLineInformation("987654321");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithNullUser() throws Exception {
        final UserResume userResume = null;
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, userResume);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("User data cannot be null.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithNullPortabilityResume() throws Exception {
        final LineInformationResume lineInformation = getLineInformation("987654321");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = null;
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Portability data cannot be null.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithNullUserName() throws Exception {
        final LineInformationResume lineInformation = getLineInformation("987654321");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, null, getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("The user name must be filled in and cannot be null or blank.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessagetWithEmptyDocumentNumber() throws Exception {
        final LineInformationResume lineInformation = getLineInformation("987654321");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final List<String> sViolations = getErrorMessageViolations(violations);
        assertTrue(sViolations.contains("The document number must be filled in and cannot be null or blank."));
    }

    @Test
    public void verifyIfReturnsErrorMessageWithNullLineNumber() throws Exception{
        final LineInformationResume lineInformation = getLineInformation(null);
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Current phone number must be filled in and cannot be null or blank.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithWrongSizeLineNumber() throws Exception{
        final LineInformationResume lineInformation = getLineInformation("123456");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Current phone number must contain between 9 and 11 digits (without special characters).", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithNullAddress() throws Exception{
        final LineInformationResume lineInformation = getLineInformation("123456789");
        final AddressResume address = null;
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("The address information cannot be null and must be filled in.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithWrongBirthOfDate() throws Exception{
        final LineInformationResume lineInformation = getLineInformation("123456789");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringFutureDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("The date of birth must be in 'yyyy-mm-dd' format and must be in the past.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithNullSourceOperator() throws Exception{
        final LineInformationResume lineInformation = getLineInformation("123456789");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume(null,"CLARO");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Portability is only allowed from the phone operator Vivo. Null or blank values are not allowed.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithEmptyTargetOperator() throws Exception{
        final LineInformationResume lineInformation = getLineInformation("123456789");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("VIVO","");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final ConstraintViolation<InputPortability> violation = violations.stream().findAny().get();
        assertEquals("Null, empty or blank values are not allowed and only the following phone operators are allowed as a destination (target): Oi, Tim, Claro e Nextel.", violation.getMessage());
    }

    @Test
    public void verifyIfReturnsErrorMessageWithWrongOperators() throws Exception{
        final LineInformationResume lineInformation = getLineInformation("123456789");
        final AddressResume address = getAddress("street", "number", "city", "country", "region");
        final UserResume user = getUser(lineInformation, address, "name", getStringPastDate(), "38894585212");
        final PortabilityResume portabilityResume = getPortabilityResume("OPERADORA_NAO_EXISTE","OPERADORA_NAO_EXISTE");
        final InputPortability inputPortability = getInputPortability(portabilityResume, user);

        final Set<ConstraintViolation<InputPortability>> violations = validator.validate(inputPortability);
        assertFalse(violations.isEmpty());
        final List<String> sViolations = getErrorMessageViolations(violations);
        assertTrue(sViolations.contains( "Portability is only allowed from the phone operator Vivo. Null or blank values are not allowed."));
        assertTrue(sViolations.contains("Null, empty or blank values are not allowed and only the following phone operators are allowed as a destination (target): Oi, Tim, Claro e Nextel."));
    }

    private static List<String> getErrorMessageViolations(Set<ConstraintViolation<InputPortability>> violations) {
        return violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
    }

    private InputPortability getInputPortability(PortabilityResume portabilityResume, UserResume user) {
        return new InputPortability(portabilityResume, user);
    }

    private PortabilityResume getPortabilityResume(String source, String target) {
        return new PortabilityResume( source, target);
    }

    private UserResume getUser(com.sysmap.srcmsportability.framework.adapters.in.dto.LineInformationResume lineInformationResume, AddressResume address, String name, String sDate, String documentNumber) {
        return new UserResume(lineInformationResume, address, name, sDate, documentNumber);
    }

    private AddressResume getAddress(String street, String number, String city, String country, String stateOrRegion) {
        return new AddressResume(street, number, city, country, stateOrRegion);
    }

    private LineInformationResume getLineInformation(String number) {
        return new LineInformationResume(number);
    }

    private static String getStringFutureDate() {
        return LocalDate.now().plusYears(1).toString();
    }

    private static String getStringPastDate() {
        return LocalDate.now().minusYears(1).toString();
    }
}