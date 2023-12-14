package com.flz.configservice.starter.config;

import com.flz.common.enums.config.ConfigType;
import com.flz.configservice.starter.client.ConfigCenterClient;
import com.flz.configservice.starter.client.ConfigCenterClientFactory;
import com.flz.configservice.starter.dto.ConfigResponseDTO;
import com.flz.configservice.starter.properties.ConfigCenterProperties;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
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

        // todo 支持active profiles        String[] activeProfiles = environment.getActiveProfiles();
        String fileName = "application";
        String applicationName = environment.resolvePlaceholders("${spring.application.name}");
        ConfigResponseDTO uniqueYmlConfig = configCenterClient.findUniqueConfig(applicationName, fileName, ConfigType.YML);
        if (uniqueYmlConfig.getContent() != null) {
            YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
            ByteArrayResource resource = new ByteArrayResource(uniqueYmlConfig.getContent().getBytes(StandardCharsets.UTF_8));
            String fullFileName = uniqueYmlConfig.getFileName() + uniqueYmlConfig.getType().getSuffix();
            try {
                List<PropertySource<?>> ymlPropertySource = yamlPropertySourceLoader.load(fullFileName, resource);
                ymlPropertySource.forEach(it -> environment.getPropertySources().addFirst(it));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
