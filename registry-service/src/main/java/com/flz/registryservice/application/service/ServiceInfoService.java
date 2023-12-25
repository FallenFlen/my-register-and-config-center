package com.flz.registryservice.application.service;

import com.flz.common.exception.BusinessException;
import com.flz.registryservice.converter.RegistryServiceConverter;
import com.flz.registryservice.domain.aggregate.ServiceInfo;
import com.flz.registryservice.domain.aggregate.ServiceInstance;
import com.flz.registryservice.domain.repository.ServiceInfoDomainRepository;
import com.flz.registryservice.presentation.dto.ServiceInfoRegisterRequestDTO;
import com.flz.registryservice.presentation.dto.ServiceInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceInfoService {
    private final ServiceInfoDomainRepository serviceInfoDomainRepository;
    private final RegistryServiceConverter registryServiceConverter = RegistryServiceConverter.INSTANCE;

    @Transactional
    public void register(ServiceInfoRegisterRequestDTO requestDTO) {
        // todo 考虑到并发注册情况其实应该加个分布式锁，但这里只是为了实现注册中心，所以没去加
        // 服务注册以服务名称name为维度，可注册多个实例
        // 1.先查服务整体信息，如果无，注册
        // 1.1 如果有，看当前请求是否能对应上已存在的实例
        // 2.如果能对应上，则更新服务状态
        // 2.1 如果不能对应上，则新增实例
        Optional<ServiceInfo> serviceInfoOptional = serviceInfoDomainRepository.findByName(requestDTO.getName());
        if (serviceInfoOptional.isEmpty()) {
            ServiceInfo serviceInfo = ServiceInfo.create(requestDTO.getName(), requestDTO.getHost(), requestDTO.getPort());
            serviceInfoDomainRepository.save(serviceInfo);
            return;
        }

        ServiceInfo serviceInfo = serviceInfoOptional.get();
        serviceInfo.getInstances().stream()
                .filter(it -> it.isSameInstance(requestDTO.getHost(), requestDTO.getPort()))
                .findFirst()
                .ifPresentOrElse(ServiceInstance::refresh, () -> serviceInfo.addInstance(requestDTO.getHost(), requestDTO.getPort()));
        serviceInfoDomainRepository.save(serviceInfo);
    }

    public ServiceInfoResponseDTO fetchServiceInfo(String name) {
        return serviceInfoDomainRepository.findByName(name)
                .map(registryServiceConverter::toDTO)
                .orElseThrow(() -> new BusinessException("service info not found with name:" + name));

    }
}
