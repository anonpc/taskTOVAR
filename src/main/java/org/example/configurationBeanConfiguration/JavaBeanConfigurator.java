package org.example.configurationBeanConfiguration;

import org.example.annotation.Injectable;
import org.reflections.Reflections;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class JavaBeanConfigurator implements BeanConfigurator {
    private Reflections scanner;

    public JavaBeanConfigurator(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Set<Class<? extends T>> getClassImplementations(Class<T> tClass) {
        final Set<Class<? extends T>> implementations = scanner.getSubTypesOf(tClass).stream()
                .filter(classImpl -> classImpl.isAnnotationPresent(Injectable.class))
                .collect(toSet());
        return implementations;
    }
}
