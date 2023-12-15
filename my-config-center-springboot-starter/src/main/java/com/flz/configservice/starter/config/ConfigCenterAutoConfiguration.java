package com.flz.configservice.starter.config;

import com.flz.configservice.starter.dynamic.DynamicValuePostProcessor;
import com.flz.configservice.starter.properties.ConfigCenterProperties;
import com.flz.configservice.starter.refresher.ConfigCenterRefresher;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigCenterProperties.class)
public class ConfigCenterAutoConfiguration {

    @Bean
    public ConfigCenterRefresher configCenterRefresher() {
        return new ConfigCenterRefresher();
    }

    @Bean
    public DynamicValuePostProcessor dynamicValuePostProcessor() {
        return new DynamicValuePostProcessor();
    }

    @Bean
    public ConfigCenterEventListener configCenterEventListener() {
        return new ConfigCenterEventListener();
    }
}
