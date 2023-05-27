package org.example.configuration;

import org.example.exception.ConfigNotFoundException;
import org.example.exception.ParamNotFoundException;
import org.example.utils.FileUtils;

import java.io.IOException;
import java.util.Map;

public class ConfigImpl implements Config {

    private final Map<String, String> params;

    public ConfigImpl(String path) {
        try {
            this.params = FileUtils.readConfig(path);
        } catch (IOException e) {
            throw new ConfigNotFoundException();
        }
    }

    @Override
    public String getParam(String name) {
        if (!params.containsKey(name)) {
            throw new ParamNotFoundException(name);
        }
        return params.get(name);
    }
}
