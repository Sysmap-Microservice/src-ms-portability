package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.domain.entities.Portability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPutStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ms-src-portability/v1")
public class PortabilityController {

    private final PortabilityService portabilityService;

    @PostMapping("/portability")
    public ResponseEntity<Portability> createPortability(@RequestBody InputPortability inputPortability) {
        return null;
    }

    @PutMapping("/portability/{portabilityId}")
    public ResponseEntity<Void> putStatusPortability(@RequestBody InputPutStatus inputPutStatus,
                                                     @PathVariable UUID portabilityId) {
        portabilityService.putStatusPortability(portabilityId, inputPutStatus.getStatus());
        return ResponseEntity.ok().build();
    }
}
