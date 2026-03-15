package com.zanosov.infrastructure.web.admin.dto;

import com.zanosov.domain.language.Language;

import java.util.UUID;

public record LanguageDto(String code, UUID id, String name, int position) {

    public static LanguageDto of(Language language) {
        return new LanguageDto(
                language.getCode().value(),
                language.getId(),
                language.getName().value(),
                language.getPosition()
        );
    }
}
