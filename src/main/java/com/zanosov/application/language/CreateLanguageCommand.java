package com.zanosov.application.language;

import com.zanosov.domain.language.LanguageCode;
import com.zanosov.domain.language.LanguageName;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public record CreateLanguageCommand(@NonNull LanguageCode code, @NonNull LanguageName name) {
    public CreateLanguageCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
