package com.zanosov.infrastructure.web.admin.facade;

import com.zanosov.application.language.LanguageService;
import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.infrastructure.web.admin.dto.LanguageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LanguageFacade {

    private final LanguageService languageService;

    public LanguageFacade(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Transactional(readOnly = true)
    public PageResult<LanguageDto> listOrderedByPosition(int page, int size) {
        PageResult<Language> result = languageService.listOrderedByPosition(page, size);
        return new PageResult<>(
                result.content().stream().map(LanguageDto::of).toList(),
                result.totalElements(),
                result.totalPages()
        );
    }
}
