package com.flz.configservice.starter.dynamic;

import org.springframework.context.ApplicationEvent;

public class DynamicValueChangeEvent extends ApplicationEvent {

    public DynamicValueChangeEvent() {
        super("");
    }
}
