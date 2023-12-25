package com.flz.registryservice.domain.aggregate;

import com.flz.common.domain.DomainAggregateRoot;
import com.flz.common.enums.registry.ServiceInstanceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class ServiceInstance extends DomainAggregateRoot {
    private String serviceId;
    private ServiceInstanceStatus status;
    private String host;
    private Integer port;
}
