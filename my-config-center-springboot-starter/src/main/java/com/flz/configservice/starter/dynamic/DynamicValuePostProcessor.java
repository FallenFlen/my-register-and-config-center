package com.flz.configservice.starter.dynamic;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DynamicValuePostProcessor implements BeanPostProcessor, EnvironmentAware, ApplicationListener<DynamicValueChangeEvent> {
    private List<DynamicFieldHolder> dynamicFieldHolders = new ArrayList<>();
    private Environment environment;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 找到bean下包含@DynamicValue的Field
        // 缓存Field
        // 监听更新event，反射修改bean的字段
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DynamicValue.class) || !field.isAnnotationPresent(Value.class)) {
                continue;
            }

            Value value = field.getAnnotation(Value.class);
            DynamicFieldHolder dynamicFieldHolder = new DynamicFieldHolder(bean, field, value.value());
            dynamicFieldHolders.add(dynamicFieldHolder);
        }

        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(DynamicValueChangeEvent event) {
        dynamicFieldHolders.forEach(it -> it.update(environment));
    }
}
