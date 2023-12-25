package com.flz.registryservice.infrastructure.persist.dataobject;

import com.flz.common.enums.registry.ServiceStatus;
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
@Table("service_info")
public class ServiceInfoDO extends BaseDO {
    private String name;
    private ServiceStatus status;
}
