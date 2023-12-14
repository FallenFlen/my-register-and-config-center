package com.flz.configservice.starter.config;

import com.flz.common.enums.config.ConfigType;
import com.flz.configservice.starter.client.ConfigCenterClient;
import com.flz.configservice.starter.dto.ConfigResponseDTO;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConfigCenterEnvironmentPostProcessor implements EnvironmentPostProcessor, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ConfigCenterClient configCenterClient = applicationContext.getBean(ConfigCenterClient.class);
        String applicationName = environment.getProperty("spring.application.name");
        // todo 支持active profiles        String[] activeProfiles = environment.getActiveProfiles();
        String fileName = "application";
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

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }
}
