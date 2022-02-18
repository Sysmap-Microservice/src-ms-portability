package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.domain.Portability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPutStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ms-src-portability/v1")
public class PortabilityController {

    private final static Logger logger = LoggerFactory.getLogger(PortabilityController.class);
    private final PortabilityService portabilityService;

    @PostMapping("/portability")
    @ResponseStatus(HttpStatus.CREATED)
    public Portability create(@RequestBody @Valid InputPortability inputPortability){
        return portabilityService.createPortability(inputPortability);
    }

    @PutMapping("/portability/{portabilityId}")
    public ResponseEntity<String> putStatusPortability(@RequestBody InputPutStatus inputPutStatus,
                                                     @PathVariable UUID portabilityId) {

        portabilityService.putStatusPortability(portabilityId, inputPutStatus.getStatus());

        var message = "SignPortability: A portabilidade " + portabilityId + " foi concluida com sucesso! Novo status: "
                + inputPutStatus.getStatus();
        logger.info(message);
        return ResponseEntity.ok("Portability: Callback recebido com sucesso!");
    }
}
