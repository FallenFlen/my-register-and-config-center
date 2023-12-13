package com.flz.configservice.application.service;

import com.flz.configservice.converter.ConfigConverter;
import com.flz.configservice.domain.aggregate.Config;
import com.flz.configservice.domain.command.ConfigSaveCommand;
import com.flz.configservice.domain.repository.ConfigDomainRepository;
import com.flz.configservice.presentation.dto.ConfigResponseDTO;
import com.flz.configservice.presentation.dto.ConfigSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ConfigResponseDTO findById(String id) {
        return converter.toDTO(configDomainRepository.findById(id));
    }

    @Transactional
    public void save(ConfigSaveRequestDTO requestDTO) {
        ConfigSaveCommand command = converter.toCommand(requestDTO);
        Config config = Config.create(command);
        configDomainRepository.save(config);
    }
}
