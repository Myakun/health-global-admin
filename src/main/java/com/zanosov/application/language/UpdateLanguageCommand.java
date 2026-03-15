package com.zanosov.application.language;

import com.zanosov.domain.language.LanguageCode;
import com.zanosov.domain.language.LanguageName;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.UUID;

public record UpdateLanguageCommand(@NonNull LanguageCode code, @NonNull UUID id, @NonNull LanguageName name) {
    public UpdateLanguageCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
