package com.flz.configservice.infrastructure.repository.jdbc;

import com.flz.configservice.infrastructure.persist.dataobject.ConfigDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConfigJdbcRepository extends CrudRepository<ConfigDO, String> {
    List<ConfigDO> findAll();
}
