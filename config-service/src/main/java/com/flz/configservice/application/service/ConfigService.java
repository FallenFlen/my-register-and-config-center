package com.flz.configservice.application.service;

import com.flz.configservice.converter.ConfigConverter;
import com.flz.configservice.domain.repository.ConfigDomainRepository;
import com.flz.configservice.presentation.dto.ConfigResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigDomainRepository configDomainRepository;
    private final ConfigConverter converter = ConfigConverter.INSTANCE;

    public List<ConfigResponseDTO> findAll() {
        return configDomainRepository.findAll().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }
}
