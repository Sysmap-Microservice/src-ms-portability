package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.domain.Address;
import com.sysmap.srcmsportability.domain.LineInformation;
import com.sysmap.srcmsportability.domain.Portability;
import com.sysmap.srcmsportability.domain.User;
import com.sysmap.srcmsportability.framework.adapters.in.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.sysmap.srcmsportability.domain.enums.CellPhoneOperator.CLARO;
import static com.sysmap.srcmsportability.domain.enums.CellPhoneOperator.VIVO;
import static com.sysmap.srcmsportability.domain.enums.StatusPortability.PROCESSING_PORTABILITY;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostPortabilityServiceTest {

    @Mock
    private PortabilityRepository repository;

    @Mock
    private UserPortabilityProducer producer;

    private PortabilityService service;

    private InputPortability inputPortability;

    private Portability portability;

    @BeforeEach
    void setUpDtoAndModelObjects() {
        this.service = new PortabilityServiceImpl(repository, producer);

        final LocalDate dateBirth = LocalDate.now().minusYears(18);
        final LineInformationResume lineInformationResume = new LineInformationResume("123456789");
        final AddressResume addressResume = new AddressResume("streetTest", "numberTest", "cityTest", "countryTest", "stateTest");
        final UserResume userResume = new UserResume(lineInformationResume, addressResume, "testeName", dateBirth.toString(), "0123456789012");
        final PortabilityResume portabilityResume = new PortabilityResume("VIVO", "CLARO");
        this.inputPortability = new InputPortability(portabilityResume, userResume);

        final LineInformation lineInformation = new LineInformation(randomUUID(), "123456789");
        final Address address = new Address(randomUUID(), "streetTest", "numberTest", "cityTest", "countryTest", "stateTest");
        final User user = new User(randomUUID(), lineInformation, address, "testeName", dateBirth, "0123456789012");
        this.portability = new Portability(randomUUID(), VIVO, CLARO, PROCESSING_PORTABILITY, user);
    }

    @Test
    @DisplayName("Should create successfully a new portability")
    void shouldCreatePortabilitySuccessfully() {

        when(repository.savePortability(any())).thenReturn(portability);

        final Portability savedPortability = service.createPortability(inputPortability);

        verify(repository, times(1)).savePortability(any(Portability.class));
        assertNotNull(savedPortability, " It can not be null");
        assertSame(portability, savedPortability, "Records should be the same");
    }
}