package com.flz.registryservice.presentation.dto;

import com.flz.common.dto.BaseResponseDTO;
import com.flz.common.enums.registry.ServiceInstanceStatus;
import com.flz.common.enums.registry.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceInfoResponseDTO extends BaseResponseDTO {
    private String name;
    private ServiceStatus status;
    private List<Instance> instances;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Instance extends BaseResponseDTO {
        private String host;
        private Integer port;
        private ServiceInstanceStatus status;
    }
}
