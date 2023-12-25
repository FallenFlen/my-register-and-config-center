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

    public static ServiceInstance create(String serviceId, String host, Integer port) {
        ServiceInstance serviceInstance = ServiceInstance.builder()
                .serviceId(serviceId)
                .host(host)
                .port(port)
                .build();
        serviceInstance.generateId();
        serviceInstance.createBySystem();
        return serviceInstance;
    }

    public void refresh() {
        this.status = ServiceInstanceStatus.ONLINE;
    }

    public boolean isSameInstance(String host, int port) {
        return this.host.equals(host) && this.port == port;
    }
}
