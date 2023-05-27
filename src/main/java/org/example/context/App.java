package org.example.context;

import lombok.Getter;
import org.example.configuration.Config;
import org.example.configuration.ConfigImpl;
import org.example.factory.BeanFactory;

public class App {
    @Getter
    private static Config config;
    public static void start() {
        config = new ConfigImpl("src/main/resources/config");
        ConsoleApplication app = BeanFactory.getInstance().getBean(ConsoleApplication.class);
        app.start();
    }
}
