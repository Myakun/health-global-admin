package com.zanosov.infrastructure.web.admin.controller.language;

import com.zanosov.domain.language.LanguageCode;
import com.zanosov.domain.language.LanguageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLanguageRequest(
        @NotBlank(message = "{language.code.required}")
        @Size(min = LanguageCode.LENGTH, max = LanguageCode.LENGTH, message = "{language.code.size}")
        String code,
        @NotBlank(message = "{language.name.required}")
        @Size(min = LanguageName.MIN_LENGTH, max = LanguageName.MAX_LENGTH, message = "{language.name.size}")
        String name) {}
