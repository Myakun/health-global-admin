package com.zanosov.domain.language;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import com.zanosov.domain.BaseEntity;
import org.jspecify.annotations.NonNull;

@Getter
public class Language extends BaseEntity {
    private final LanguageCode code;

    private final LanguageName name;

    private final int position;

    public Language(
            @NonNull LanguageCode code,
            @NonNull Instant createdAt,
            @NonNull UUID id,
            @NonNull LanguageName name,
            int position,
            @NonNull Instant updatedAt) {
        super(createdAt, id, updatedAt);
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.position = position;
    }
}
