package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ms-src-portability/v1")
public class PortabilityController {

    @Autowired
    private PortabilityService portabilityService;

    @PostMapping("/portability")
    public void createPortability(
            @RequestBody InputPortability inputPortability)
    {
        portabilityService.createPortability(inputPortability.getPortability());
    }
}
