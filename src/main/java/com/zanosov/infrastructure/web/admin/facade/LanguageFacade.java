package com.zanosov.infrastructure.web.admin.facade;

import com.zanosov.application.language.CreateLanguageCommand;
import com.zanosov.application.language.LanguageService;
import com.zanosov.application.language.UpdateLanguageCommand;
import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageCode;
import com.zanosov.domain.language.LanguageName;
import com.zanosov.domain.language.LanguageNotFoundException;
import com.zanosov.infrastructure.web.admin.controller.language.CreateLanguageRequest;
import com.zanosov.infrastructure.web.admin.controller.language.UpdateLanguageRequest;
import com.zanosov.infrastructure.web.admin.dto.LanguageDto;
import com.zanosov.infrastructure.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class LanguageFacade {

    private final LanguageService languageService;

    public LanguageFacade(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Transactional
    public LanguageDto create(CreateLanguageRequest request) {
        var command = new CreateLanguageCommand(
                new LanguageCode(request.code()),
                new LanguageName(request.name())
        );

        return LanguageDto.of(languageService.create(command));
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            languageService.deleteById(id);
        } catch (LanguageNotFoundException ex) {
            // Language already deleted?
        }
    }

    @Transactional(readOnly = true)
    public LanguageDto findById(UUID id) {
        return languageService.findById(id)
                .map(LanguageDto::of)
                .orElseThrow(() -> new ObjectNotFoundException(Language.class, id));
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

    @Transactional
    public LanguageDto update(UUID id, UpdateLanguageRequest request) {
        var command = new UpdateLanguageCommand(
                new LanguageCode(request.code()),
                id,
                new LanguageName(request.name())
        );

        try {
            return LanguageDto.of(languageService.update(command));
        } catch (LanguageNotFoundException ex) {
            throw new ObjectNotFoundException(Language.class, id);
        }
    }
}
