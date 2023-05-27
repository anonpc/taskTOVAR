package org.example.factory;

import org.example.configuration.BeanConfiguration;
import org.example.configuration.proccesor.JavaBeanConfiguration;
import org.example.configuration.proccesor.PostProcessor;
import org.example.configurationBeanConfiguration.BeanConfigurator;
import org.example.configurationBeanConfiguration.JavaBeanConfigurator;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class BeanFactory {
    private static BeanFactory INSTANCE;
    private Map<String, Object> beanMap;
    private final BeanConfiguration beanConfiguration;
    private final BeanConfigurator beanConfigurator;

    private BeanFactory() {
        beanConfiguration = new JavaBeanConfiguration();
        beanConfigurator = new JavaBeanConfigurator(beanConfiguration.getPackageToScan());

        beanMap = new HashMap<>();
    }

    public static BeanFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BeanFactory();
        }
        return INSTANCE;
    }


    public <T> T getBean(Class<T> tClass) {
        Class<? extends T> implClass = tClass;
        if (implClass.isInterface()) {
            final Set<Class<? extends T>> classImplementations = beanConfigurator.getClassImplementations(tClass);
            if (classImplementations.size() != 1) {
                throw new RuntimeException("Interface has 0 or more than 1 implementation");
            }
            implClass = classImplementations.stream().findFirst().get();
        }
        return initBean(implClass);
    }

    public <T> List<T> getBeans(Class<T> tClass) {
        Class<? extends T> implClass = tClass;
        if (tClass.isInterface()) {
            final Set<Class<? extends T>> classImplementations = beanConfigurator.getClassImplementations(tClass);
            return classImplementations.stream()
                    .map(this::initBean)
                    .collect(toList());
        }
        return Collections.singletonList(initBean(implClass));
    }

    private <T> T initBean(Class<T> implClass) {
        try {
            T bean = implClass.getDeclaredConstructor().newInstance();

            for (PostProcessor p : beanConfiguration.processors()) {
                bean = p.process(bean, this);
            }

            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Exception while bean initialization", e);
        }
    }
}
