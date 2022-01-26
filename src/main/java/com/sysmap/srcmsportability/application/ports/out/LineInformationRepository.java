package com.sysmap.srcmsportability.application.ports.out;

import com.sysmap.srcmsportability.domain.entities.LineInformation;

import java.util.Optional;
import java.util.UUID;

public interface LineInformationRepository {

    LineInformation saveLine(LineInformation lineInformation);
    Optional<LineInformation> findLineById(UUID lineId);
    Optional<LineInformation> findLineByNumber(String lineNumber);
}
