package com.zanosov.domain.language;

public class LanguageNameAlreadyExistsException extends RuntimeException {

    public LanguageNameAlreadyExistsException(String name) {
        super("Language with name '%s' already exists".formatted(name));
    }
}
