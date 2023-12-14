package com.flz.configservice.starter.config;

import org.springframework.core.env.EnumerablePropertySource;

public class ConfigCenterPropertySource extends EnumerablePropertySource<ConfigCenter> {
    public ConfigCenterPropertySource(String name, ConfigCenter source) {
        super(name, source);
    }

    protected ConfigCenterPropertySource(String name) {
        super(name);
    }

    @Override
    public String[] getPropertyNames() {
        return new String[0];
    }

    @Override
    public Object getProperty(String name) {
        return null;
    }

    @Override
    public boolean containsProperty(String name) {
        return false;
    }
}
