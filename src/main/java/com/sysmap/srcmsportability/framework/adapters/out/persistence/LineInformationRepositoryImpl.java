package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.out.LineInformationRepository;
import com.sysmap.srcmsportability.application.ports.in.entities.LineInformation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class LineInformationRepositoryImpl implements LineInformationRepository {

    @Autowired
    LineInformationPostgreRepository repository;

    @Override
    public LineInformation saveLine(LineInformation lineInformation) {
        return this.repository.save(lineInformation);
    }

    @Override
    public Optional<LineInformation> findLineById(UUID lineId) {
        return Optional.empty();
    }

    @Override
    public Optional<LineInformation> findLineByNumber(String lineNumber) {
        return Optional.empty();
    }
}
