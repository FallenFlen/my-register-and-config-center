package com.flz.registryservice.infrastructure.persist.dataobject;

import com.flz.common.enums.registry.ServiceInstanceStatus;
import com.flz.common.persist.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("service_instance")
public class ServiceInstanceDO extends BaseDO {
    private String serviceId;
    private ServiceInstanceStatus status;
    private String host;
    private Integer port;
}
