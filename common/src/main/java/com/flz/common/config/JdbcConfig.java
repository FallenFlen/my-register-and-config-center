package com.flz.common.config;

import com.flz.common.persist.entity.BaseDO;
import com.flz.common.utils.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

import java.time.LocalDateTime;

@Configuration
public class JdbcConfig {

    @Bean
    public BeforeSaveCallback<BaseDO> beforeSaveCallback() {
        return (dataObject, aggregateChange) -> {
            if (dataObject.getId() == null) {
                dataObject.setId(IdGenerator.randomId());
                dataObject.setCreateTime(LocalDateTime.now());
            }
            dataObject.setUpdateTime(LocalDateTime.now());
            return dataObject;
        };
    }
}
