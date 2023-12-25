package com.flz.registryservice.domain.aggregate;

import com.flz.common.domain.DomainAggregateRoot;
import com.flz.common.enums.registry.ServiceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class ServiceInfo extends DomainAggregateRoot {
    private String name;
    private ServiceStatus status;
    private List<ServiceInstance> instances;
}
