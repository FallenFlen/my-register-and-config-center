package com.flz.configservice.starter.refresher;

import com.flz.common.enums.config.ConfigType;
import com.flz.configservice.starter.client.ConfigCenterClient;
import com.flz.configservice.starter.client.ConfigCenterClientFactory;
import com.flz.configservice.starter.dto.ConfigResponseDTO;
import com.flz.configservice.starter.dynamic.DynamicValueChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ByteArrayResource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConfigCenterRefresher implements ApplicationEventPublisherAware, EnvironmentAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigCenterRefresher.class);
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newSingleThreadScheduledExecutor();
    private ApplicationEventPublisher applicationEventPublisher;
    private Environment environment;

    @PostConstruct
    public void startSchedule() {
        LOGGER.info("start scheduled fetching config");
        // 每10秒拉取最新的config进行刷新
        SCHEDULED_EXECUTOR.scheduleAtFixedRate(this::refresh, 10, 10, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void shutdown() {
        LOGGER.info("stop scheduled fetching config");
        SCHEDULED_EXECUTOR.shutdown();
    }

    private void refresh() {
        String[] activeProfiles = environment.getActiveProfiles();
        String fileName = "application";
        if (activeProfiles.length > 0) {
            fileName += "-" + activeProfiles[0];
        }
        String applicationName = environment.resolvePlaceholders("${spring.application.name}");
        ConfigCenterClient configCenterClient = ConfigCenterClientFactory.get();
        ConfigResponseDTO uniquePropertiesConfig = configCenterClient.findUniqueConfig(applicationName, fileName, ConfigType.PROPERTIES);
        if (uniquePropertiesConfig.getContent() != null) { // properties 优先于yml
            insertConfigIntoEnv(uniquePropertiesConfig, new PropertiesPropertySourceLoader(), (ConfigurableEnvironment) environment);
        } else { // yml
            ConfigResponseDTO uniqueYmlConfig = configCenterClient.findUniqueConfig(applicationName, fileName, ConfigType.YML);
            if (uniqueYmlConfig.getContent() != null) {
                insertConfigIntoEnv(uniqueYmlConfig, new YamlPropertySourceLoader(), (ConfigurableEnvironment) environment);
            }
        }
        applicationEventPublisher.publishEvent(new DynamicValueChangeEvent());
        LOGGER.info("config refreshed!");
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

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
