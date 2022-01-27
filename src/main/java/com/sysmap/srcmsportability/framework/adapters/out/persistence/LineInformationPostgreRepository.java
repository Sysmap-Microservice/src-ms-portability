package com.sysmap.srcmsportability.framework.adapters.out.persistence;

import com.sysmap.srcmsportability.application.ports.in.entities.LineInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LineInformationPostgreRepository extends CrudRepository<LineInformation, UUID> {
}
