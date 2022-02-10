package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.domain.Portability;
import com.sysmap.srcmsportability.domain.enums.CellPhoneOperator;
import com.sysmap.srcmsportability.domain.enums.StatusPortability;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.framework.adapters.in.exceptions.PortabilityNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PutPortabilityServiceTest {

    private PortabilityService portabilityService;

    @Mock
    private PortabilityRepository portabilityRepository;
    @Mock
    private UserPortabilityProducer userPortabilityProducer;

    @BeforeEach
    public void setup() {
        portabilityService = new PortabilityServiceImpl(portabilityRepository, userPortabilityProducer);
    }

    @Spy
    Portability portability = Portability.builder()
            .status(StatusPortability.PORTED)
            .target(CellPhoneOperator.CLARO)
            .source(CellPhoneOperator.VIVO)
            .build();
    UUID portabilityId = UUID.randomUUID();

    @Test
    void shouldPutStatusPortability() {

        when(portabilityRepository.findPortabilityById(portabilityId)).thenReturn(Optional.of(portability));

        portabilityService.putStatusPortability(portabilityId, StatusPortability.PORTED);

        assertEquals(StatusPortability.PORTED, portability.getStatus());

    }

    @Test
    void shouldReturnPortabilityNotFoundException() {

        when(portabilityRepository.findPortabilityById(portabilityId)).thenReturn(Optional.empty());

        assertThrows(PortabilityNotFound.class, () -> portabilityService.putStatusPortability(portabilityId, StatusPortability.PORTED));
    }

}