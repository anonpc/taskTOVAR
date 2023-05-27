package org.example.configuration.proccesor;

import org.example.configuration.BeanConfiguration;

import java.util.List;

public class JavaBeanConfiguration implements BeanConfiguration {
    @Override
    public String getPackageToScan() {
        return "org.example";
    }

    @Override
    public List<PostProcessor> processors() {
        return List.of(new InjectAnnotationPostProcessor());
    }
}
