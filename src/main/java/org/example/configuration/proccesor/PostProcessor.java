package org.example.configuration.proccesor;

import org.example.factory.BeanFactory;

public interface PostProcessor {
    <T> T process(T bean, BeanFactory beanFactory);
}
