package com.flz.configservice.starter.properties;

import com.flz.configservice.starter.constants.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = Constant.PROPERTIES_PREFIX)
public class ConfigCenterProperties {
    private String url;

    public ConfigCenterProperties(String url) {
        this.url = url;
    }

    public ConfigCenterProperties() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
