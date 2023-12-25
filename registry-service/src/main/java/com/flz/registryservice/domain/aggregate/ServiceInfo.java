package com.flz.registryservice.domain.aggregate;

import com.flz.common.domain.DomainAggregateRoot;
import com.flz.common.enums.registry.ServiceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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

    public static ServiceInfo create(String name, String host, Integer port) {
        ServiceInfo serviceInfo = ServiceInfo.builder()
                .name(name)
                .status(ServiceStatus.ONLINE)
                .instances(new ArrayList<>())
                .build();
        serviceInfo.generateId();
        serviceInfo.createBySystem();
        ServiceInstance serviceInstance = ServiceInstance.create(serviceInfo.getId(), host, port);
        serviceInfo.getInstances().add(serviceInstance);
        return serviceInfo;
    }

    public void addInstance(String host, Integer port) {
        ServiceInstance instance = ServiceInstance.create(id, host, port);
        this.instances.add(instance);
    }

    public void assembleInstances(List<ServiceInstance> instances) {
        this.instances = instances;
    }
}
