package com.zanosov.application.language;

import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public PageResult<Language> listOrderedByPosition(int page, int size) {
        return languageRepository.findAllOrderedByPosition(page, size);
    }
}
