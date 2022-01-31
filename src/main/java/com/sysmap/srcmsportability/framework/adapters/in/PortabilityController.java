package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPutStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ms-src-portability/v1")
public class PortabilityController {

    private final PortabilityService portabilityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Portability create(@RequestBody InputPortability portability){
        System.out.println(portability);
        return portabilityService.createPortability(portability);
    }

    @PutMapping("/portability/{portabilityId}")
    public ResponseEntity<Void> putStatusPortability(@RequestBody InputPutStatus inputPutStatus,
                                                     @PathVariable UUID portabilityId) {
        portabilityService.putStatusPortability(portabilityId, inputPutStatus.getStatus());
        return ResponseEntity.ok().build();
    }
}
