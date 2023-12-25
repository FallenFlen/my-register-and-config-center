package com.flz.registryservice.domain.repository;

import com.flz.registryservice.converter.RegistryServiceConverter;
import com.flz.registryservice.domain.aggregate.ServiceInfo;
import com.flz.registryservice.domain.aggregate.ServiceInstance;
import com.flz.registryservice.infrastructure.persist.dataobject.ServiceInfoDO;
import com.flz.registryservice.infrastructure.persist.dataobject.ServiceInstanceDO;
import com.flz.registryservice.infrastructure.repository.jdbc.ServiceInfoJdbcRepository;
import com.flz.registryservice.infrastructure.repository.jdbc.ServiceInstanceJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ServiceInfoDomainRepository {
    private final ServiceInfoJdbcRepository serviceInfoJdbcRepository;
    private final ServiceInstanceJdbcRepository serviceInstanceJdbcRepository;
    private final RegistryServiceConverter converter = RegistryServiceConverter.INSTANCE;

    public Optional<ServiceInfo> findByName(String name) {
        Optional<ServiceInfo> serviceInfo = serviceInfoJdbcRepository.findByName(name)
                .map(converter::toDomain);
        serviceInfo.ifPresent(it -> {
            List<ServiceInstance> instances = serviceInstanceJdbcRepository.findAllByServiceId(it.getId()).stream()
                    .map(converter::toDomain)
                    .collect(Collectors.toList());
            it.assembleInstances(instances);
        });
        return serviceInfo;
    }

    public void save(ServiceInfo serviceInfo) {
        ServiceInfoDO serviceInfoDO = converter.toDO(serviceInfo);
        serviceInfoJdbcRepository.save(serviceInfoDO);
        List<ServiceInstanceDO> instanceDOs = serviceInfo.getInstances().stream()
                .map(converter::toDO)
                .collect(Collectors.toList());
        serviceInstanceJdbcRepository.saveAll(instanceDOs);
    }
}
