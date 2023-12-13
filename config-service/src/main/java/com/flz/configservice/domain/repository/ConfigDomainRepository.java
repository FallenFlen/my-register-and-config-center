package com.flz.configservice.domain.repository;

import com.flz.common.exception.BusinessException;
import com.flz.configservice.converter.ConfigConverter;
import com.flz.configservice.domain.aggregate.Config;
import com.flz.configservice.infrastructure.repository.jdbc.ConfigJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ConfigDomainRepository {
    private final ConfigJdbcRepository configJdbcRepository;
    private final ConfigConverter converter = ConfigConverter.INSTANCE;

    public List<Config> findAll() {
        return configJdbcRepository.findAll().stream()
                .map(converter::toDomain)
                .collect(Collectors.toList());
    }

    public Config findById(String id) {
        return configJdbcRepository.findById(id)
                .map(converter::toDomain)
                .orElseThrow(() -> new BusinessException("config not found with id:" + id));
    }

    public void save(Config config) {
        configJdbcRepository.save(converter.toDO(config));
    }

    public void deleteById(String id) {
        configJdbcRepository.deleteById(id);
    }
}
