package com.flz.configservice.starter.config;

import com.flz.common.enums.config.ConfigType;
import com.flz.configservice.starter.client.ConfigCenterClient;
import com.flz.configservice.starter.client.ConfigCenterClientFactory;
import com.flz.configservice.starter.dto.ConfigResponseDTO;
import com.flz.configservice.starter.properties.ConfigCenterProperties;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConfigCenterEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String url = environment.resolvePlaceholders("${myconfigcenter.url}");
        ConfigCenterProperties configCenterProperties = new ConfigCenterProperties(url);
        ConfigCenterClient configCenterClient = ConfigCenterClientFactory.getInstance(configCenterProperties);
        String[] activeProfiles = environment.getActiveProfiles();
        String fileName = "application";
        if (activeProfiles.length > 0) {
            fileName += "-" + activeProfiles[0];
        }
        String applicationName = environment.resolvePlaceholders("${spring.application.name}");

        ConfigResponseDTO uniquePropertiesConfig = configCenterClient.findUniqueConfig(applicationName, fileName, ConfigType.PROPERTIES);
        if (uniquePropertiesConfig.getContent() != null) { // properties 优先于yml
            insertConfigIntoEnv(uniquePropertiesConfig, new PropertiesPropertySourceLoader(), environment);
        } else { // yml
            ConfigResponseDTO uniqueYmlConfig = configCenterClient.findUniqueConfig(applicationName, fileName, ConfigType.YML);
            if (uniqueYmlConfig.getContent() != null) {
                insertConfigIntoEnv(uniqueYmlConfig, new YamlPropertySourceLoader(), environment);
            }
        }
    }

    private void insertConfigIntoEnv(ConfigResponseDTO config, PropertySourceLoader propertySourceLoader, ConfigurableEnvironment environment) {
        ByteArrayResource resource = new ByteArrayResource(config.getContent().getBytes(StandardCharsets.UTF_8));
        String fullFileName = config.getFileName() + config.getType().getSuffix();
        try {
            List<PropertySource<?>> ymlPropertySource = propertySourceLoader.load(fullFileName, resource);
            ymlPropertySource.forEach(it -> environment.getPropertySources().addFirst(it));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
