package com.example.spring_boot_app.common.exception;

public class ConfigException extends Exception{

    private static final long serialVersionUID = 1L;

    public ConfigException(String message) {
        super(message);
    }
}