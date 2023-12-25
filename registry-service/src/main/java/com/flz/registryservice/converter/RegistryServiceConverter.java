package com.flz.registryservice.converter;

import com.flz.registryservice.domain.aggregate.ServiceInfo;
import com.flz.registryservice.domain.aggregate.ServiceInstance;
import com.flz.registryservice.infrastructure.persist.dataobject.ServiceInfoDO;
import com.flz.registryservice.infrastructure.persist.dataobject.ServiceInstanceDO;
import com.flz.registryservice.presentation.dto.ServiceInfoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistryServiceConverter {
    RegistryServiceConverter INSTANCE = Mappers.getMapper(RegistryServiceConverter.class);

    ServiceInfoResponseDTO toDTO(ServiceInfo serviceInfo);

    ServiceInfoDO toDO(ServiceInfo serviceInfo);

    ServiceInfo toDomain(ServiceInfoDO serviceInfo);

    ServiceInstanceDO toDO(ServiceInstance serviceInstance);

    ServiceInstance toDomain(ServiceInstanceDO serviceInstance);
}
