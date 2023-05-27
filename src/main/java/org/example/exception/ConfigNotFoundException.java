package org.example.exception;

public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException() {
        super("конфиг не найден");
    }
}
