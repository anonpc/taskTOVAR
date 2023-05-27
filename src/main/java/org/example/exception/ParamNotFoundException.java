package org.example.exception;

public class ParamNotFoundException extends RuntimeException{
    public ParamNotFoundException() {
        super("параметр не найден");
    }

    public ParamNotFoundException(String paramName) {
        super(String.format("параметр с названием %s не найден", paramName));
    }
}
