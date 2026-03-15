package com.zanosov.domain.language;

import com.zanosov.domain.BaseEntity;
import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
public class LanguageTranslation extends BaseEntity {
    private final LanguageTranslationName name;

    private final UUID sourceLanguageId;

    private final UUID translationLanguageId;

    public LanguageTranslation(
            @NonNull Instant createdAt,
            @NonNull UUID id,
            @NonNull LanguageTranslationName name,
            @NonNull UUID sourceLanguageId,
            @NonNull UUID translationLanguageId,
            @NonNull Instant updatedAt) {
        super(createdAt, id, updatedAt);
        this.sourceLanguageId = Objects.requireNonNull(sourceLanguageId, "sourceLanguageId must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.translationLanguageId = Objects.requireNonNull(translationLanguageId, "translationLanguageId must not be null");
    }
}
