package com.flz.configservice.infrastructure.repository.jdbc;

import com.flz.common.enums.config.ConfigType;
import com.flz.configservice.infrastructure.persist.dataobject.ConfigDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigJdbcRepository extends CrudRepository<ConfigDO, String> {
    List<ConfigDO> findAll();

    Optional<ConfigDO> findByBelongingApplicationNameAndFileNameAndType(String belongingApplicationName,
                                                                        String fileName,
                                                                        ConfigType type);
}
