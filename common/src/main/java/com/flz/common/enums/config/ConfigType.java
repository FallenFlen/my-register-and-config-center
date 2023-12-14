package com.flz.common.enums.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConfigType {
    YML(".yml"),
    PROPERTIES(".properties");
    private final String suffix;
}
