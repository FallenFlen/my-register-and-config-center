package com.flz.configservice.starter.client;

import com.flz.configservice.starter.properties.ConfigCenterProperties;

public class ConfigCenterClientFactory {
    private static volatile ConfigCenterClient configCenterClient;

    public static ConfigCenterClient getInstance(ConfigCenterProperties properties) {
        if (configCenterClient == null) {
            synchronized (ConfigCenterClientFactory.class) {
                if (configCenterClient == null) {
                    configCenterClient = new ConfigCenterClient(properties);
                }
            }
        }
        return configCenterClient;
    }
}
