package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPutStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("ms-src-portability/v1")
public class PortabilityController {

    @Autowired
    private PortabilityService portabilityService;

    @PostMapping("/portability")
    public ResponseEntity createPortability(@RequestBody InputPortability inputPortability) {
        portabilityService.createPortability(inputPortability.getPortability());
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PutMapping("/portability/{portabilityId}")
    public ResponseEntity putStatusPortability(@RequestBody InputPutStatus inputPutStatus, @PathVariable UUID portabilityId)
            throws ChangeSetPersister.NotFoundException {
        return portabilityService.putStatusPortability(portabilityId, inputPutStatus.getStatus());
    }
}
