package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

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

    @BeforeEach
    public void setup() {
        portabilityService = new PortabilityServiceImpl(portabilityRepository);
    }

    @Spy
    Portability portability = new Portability();
    UUID portabilityId = UUID.randomUUID();

    @Test
    void shouldPutStatusPortability() throws ChangeSetPersister.NotFoundException {

        when(portabilityRepository.findPortabilityById(portabilityId)).thenReturn(Optional.of(portability));

        portabilityService.putStatusPortability(portabilityId, StatusPortability.PORTADO);

        assertEquals(StatusPortability.PORTADO, portability.getStatus());

    }

    @Test
    void shouldReturnPortabilityNotFoundException() {

        when(portabilityRepository.findPortabilityById(portabilityId)).thenReturn(Optional.empty());

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            portabilityService.putStatusPortability(portabilityId, StatusPortability.PORTADO);
        });
    }

}