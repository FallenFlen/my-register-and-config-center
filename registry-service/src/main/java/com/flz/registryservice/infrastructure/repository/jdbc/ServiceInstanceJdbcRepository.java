package com.flz.registryservice.infrastructure.repository.jdbc;

import com.flz.registryservice.infrastructure.persist.dataobject.ServiceInstanceDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceInstanceJdbcRepository extends CrudRepository<ServiceInstanceDO, String> {
    List<ServiceInstanceDO> findAllByServiceId(String serviceId);
}
