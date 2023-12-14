package com.flz.configservice.application.service;

import com.flz.common.enums.config.ConfigStatus;
import com.flz.common.enums.config.ConfigType;
import com.flz.common.exception.BusinessException;
import com.flz.configservice.converter.ConfigConverter;
import com.flz.configservice.domain.aggregate.Config;
import com.flz.configservice.domain.command.ConfigUpsertCommand;
import com.flz.configservice.domain.repository.ConfigDomainRepository;
import com.flz.configservice.presentation.dto.ConfigResponseDTO;
import com.flz.configservice.presentation.dto.ConfigUpsertRequestDTO;
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
    public void save(ConfigUpsertRequestDTO requestDTO) {
        ConfigUpsertCommand command = converter.toCommand(requestDTO);
        checkDuplication(command.getBelongingApplicationName(), command.getFileName(), command.getType());
        Config config = Config.create(command);
        configDomainRepository.save(config);
    }

    @Transactional
    public void edit(String id, ConfigUpsertRequestDTO requestDTO) {
        Config config = configDomainRepository.findById(id);
        ConfigUpsertCommand command = converter.toCommand(requestDTO);
        checkDuplication(command.getBelongingApplicationName(), command.getFileName(), command.getType());
        config.update(command);
        configDomainRepository.save(config);
    }

    private void checkDuplication(String belongingApplicationName,
                                  String fileName,
                                  ConfigType type) {
        if (configDomainRepository.findByBelongingApplicationNameAndFileNameAndType(belongingApplicationName, fileName, type).isPresent()) {
            throw new BusinessException(String.format("config duplicated:%s", String.join(belongingApplicationName, fileName, type.getSuffix())));
        }
    }

    @Transactional
    public void delete(String id) {
        configDomainRepository.deleteById(id);
    }

    @Transactional
    public void switchStatus(String id, ConfigStatus configStatus) {
        Config config = configDomainRepository.findById(id);
        config.switchStatus(configStatus);
        configDomainRepository.save(config);
    }
}
