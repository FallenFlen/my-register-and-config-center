package com.flz.configservice.starter.dynamic;

import org.springframework.core.env.Environment;

import java.lang.reflect.Field;

public class DynamicFieldHolder {
    private Object bean;
    private Field field;
    private String placeholder;

    public DynamicFieldHolder(Object bean, Field field, String placeholder) {
        this.bean = bean;
        this.field = field;
        this.placeholder = placeholder;
    }

    public DynamicFieldHolder() {
    }

    public void update(Environment environment) {
        // 此时env中已添加新的config
        field.setAccessible(true);
        try {
            field.set(bean, environment.resolvePlaceholders(placeholder));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
