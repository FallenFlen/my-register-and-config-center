package com.flz.configservice.starter.client;

import com.flz.common.enums.config.ConfigType;
import com.flz.common.utils.JsonUtils;
import com.flz.configservice.starter.constants.Constant;
import com.flz.configservice.starter.dto.ConfigResponseDTO;
import com.flz.configservice.starter.properties.ConfigCenterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ConfigCenterClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigCenterClient.class);
    private RestTemplate restTemplate;
    private ConfigCenterProperties configCenterProperties;

    public ConfigCenterClient(ConfigCenterProperties configCenterProperties) {
        this.configCenterProperties = configCenterProperties;
        this.restTemplate = new RestTemplate();
    }

    public ConfigResponseDTO findUniqueConfig(String belongingApplicationName,
                                              String fileName,
                                              ConfigType type) {
        String url = configCenterProperties.getUrl() + Constant.FIND_UNIQUE_CONFIG_API;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("belongingApplicationName", belongingApplicationName)
                .queryParam("fileName", fileName)
                .queryParam("type", type);
        ConfigResponseDTO configResponseDTO = restTemplate.getForObject(builder.build().encode().toUri(), ConfigResponseDTO.class);
        LOGGER.info("find unique config:{}", JsonUtils.silentMarshal(configResponseDTO));
        return configResponseDTO;
    }
}
