package org.example.configurationBeanConfiguration;

import java.util.Set;

public interface BeanConfigurator {
    <T> Set<Class<? extends T>> getClassImplementations(Class<T> tClass);
}
