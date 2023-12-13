package com.flz.configservice.converter;

import com.flz.configservice.domain.aggregate.Config;
import com.flz.configservice.infrastructure.persist.dataobject.ConfigDO;
import com.flz.configservice.presentation.dto.ConfigResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigConverter {
    ConfigConverter INSTANCE = Mappers.getMapper(ConfigConverter.class);

    ConfigResponseDTO toDTO(Config config);

    Config toDomain(ConfigDO configDO);

    ConfigDO toDO(Config config);
}
