package com.flz.registryservice.infrastructure.repository.jdbc;

import com.flz.registryservice.infrastructure.persist.dataobject.ServiceInfoDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ServiceInfoJdbcRepository extends CrudRepository<ServiceInfoDO, String> {
    Optional<ServiceInfoDO> findByName(String name);
}
