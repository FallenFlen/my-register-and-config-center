package com.flz.configservice.starter.dynamic;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DynamicValuePostProcessor implements BeanPostProcessor, EnvironmentAware, ApplicationListener<DynamicValueChangeEvent> {
    private Map<String, List<DynamicFieldHolder>> fieldMap = new HashMap<>();
    private Environment environment;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 找到bean下包含@DynamicValue的Field
        // 缓存Field
        // 监听更新event，反射修改bean的字段
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DynamicValue.class)) {
                continue;
            }

            DynamicValue dynamicValue = field.getAnnotation(DynamicValue.class);
            DynamicFieldHolder dynamicFieldHolder = new DynamicFieldHolder(bean, field, dynamicValue.placeholder());
            fieldMap.computeIfAbsent(dynamicValue.placeholder(), (k) -> new ArrayList<>()).add(dynamicFieldHolder);
        }

        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(DynamicValueChangeEvent event) {
        String placeholder = event.getPlaceholder();
        Optional.ofNullable(fieldMap.get(placeholder))
                .ifPresent(holders -> {
                    holders.forEach(holder -> {
                        holder.update(environment);
                    });
                });
    }
}
