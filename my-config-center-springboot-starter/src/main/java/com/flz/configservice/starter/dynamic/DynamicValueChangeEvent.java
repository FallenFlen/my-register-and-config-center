package com.flz.configservice.starter.dynamic;

import org.springframework.context.ApplicationEvent;

public class DynamicValueChangeEvent extends ApplicationEvent {
    private String placeholder;

    public DynamicValueChangeEvent(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }
}
