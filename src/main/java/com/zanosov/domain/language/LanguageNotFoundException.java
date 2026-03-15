package com.zanosov.domain.language;

import java.util.UUID;

public class LanguageNotFoundException extends RuntimeException {

    public LanguageNotFoundException(UUID id) {
        super("Language with id '%s' not found".formatted(id));
    }
}
