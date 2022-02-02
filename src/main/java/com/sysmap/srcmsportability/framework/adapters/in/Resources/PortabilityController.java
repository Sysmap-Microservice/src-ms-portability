package com.sysmap.srcmsportability.framework.adapters.in.Resources;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.domain.entities.exceptions.PortabilityNotFound;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPutStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ms-src-portability/v1")
public class PortabilityController {

    private static Logger logger = LoggerFactory.getLogger(PortabilityController.class);
    private final PortabilityService portabilityService;

    @PostMapping("/portability")
    @ResponseStatus(HttpStatus.CREATED)
    public Portability create(@RequestBody InputPortability inputPortability){
        return portabilityService.createPortability(inputPortability);
    }

    @PutMapping("/portability/{portabilityId}")
    public ResponseEntity<Void> putStatusPortability(@RequestBody InputPutStatus inputPutStatus,
                                                     @PathVariable UUID portabilityId) {
        portabilityService.putStatusPortability(portabilityId, inputPutStatus.getStatus());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/callback")
    public ResponseEntity<String> callback(@RequestParam("message") String message){
        if(message.isEmpty()){
            logger.warn("Callback não recebido!");
            throw new PortabilityNotFound("Callback não recebido!");
        }
        logger.info(message);
        return ResponseEntity.ok("Portability: Callback recebido com sucesso!");
    }

}
