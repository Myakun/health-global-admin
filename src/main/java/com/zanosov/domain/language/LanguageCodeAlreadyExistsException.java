package com.zanosov.domain.language;

public class LanguageCodeAlreadyExistsException extends RuntimeException {

    public LanguageCodeAlreadyExistsException(String code) {
        super("Language with code '%s' already exists".formatted(code));
    }
}
