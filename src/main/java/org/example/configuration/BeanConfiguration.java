package org.example.configuration;

import org.example.configuration.proccesor.PostProcessor;

import java.util.List;

public interface BeanConfiguration {
    String getPackageToScan();
    List<PostProcessor> processors();
}
