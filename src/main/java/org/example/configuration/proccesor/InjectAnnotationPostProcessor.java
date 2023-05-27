package org.example.configuration.proccesor;

import org.example.annotation.Inject;
import org.example.factory.BeanFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

//сделать ValueAnnotationPostProcessor
public class InjectAnnotationPostProcessor implements PostProcessor {
    @Override
    public <T> T process(T bean, BeanFactory beanFactory) {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        if (Collection.class.isAssignableFrom(field.getType())) {
                            final ParameterizedType type = (ParameterizedType) field.getGenericType();
                            final Type actualTypeArgument = type.getActualTypeArguments()[0];

                            field.set(bean, beanFactory.getBeans((Class) actualTypeArgument));
                        } else {
                            field.set(bean, beanFactory.getBean(field.getType()));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("No access to field");
                    }
                });
        return bean;
    }
}
