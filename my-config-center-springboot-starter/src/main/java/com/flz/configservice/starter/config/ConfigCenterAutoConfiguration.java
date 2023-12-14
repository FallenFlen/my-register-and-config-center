package com.flz.configservice.starter.config;

import com.flz.configservice.starter.client.ConfigCenterClient;
import com.flz.configservice.starter.properties.ConfigCenterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigCenterProperties.class)
public class ConfigCenterAutoConfiguration {
    @Autowired
    private ConfigCenterProperties configCenterProperties;

    @Bean
    public ConfigCenterClient configCenterClient() {
        return new ConfigCenterClient(configCenterProperties);
    }
}
