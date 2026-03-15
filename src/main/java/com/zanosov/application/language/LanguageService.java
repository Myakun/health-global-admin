package com.zanosov.application.language;

import com.zanosov.domain.IdGenerator;
import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageCodeAlreadyExistsException;
import com.zanosov.domain.language.LanguageNameAlreadyExistsException;
import com.zanosov.domain.language.LanguageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LanguageService {

    private final IdGenerator idGenerator;

    private final LanguageRepository languageRepository;

    public LanguageService(IdGenerator idGenerator, LanguageRepository languageRepository) {
        this.idGenerator = idGenerator;
        this.languageRepository = languageRepository;
    }

    public Language create(CreateLanguageCommand command) {
        if (languageRepository.existsByCode(command.code())) {
            throw new LanguageCodeAlreadyExistsException(command.code().value());
        }

        if (languageRepository.existsByName(command.name())) {
            throw new LanguageNameAlreadyExistsException(command.name().value());
        }

        int position = languageRepository.findMaxPosition() + 1;
        var now = Instant.now();
        var language = new Language(command.code(), now, idGenerator.generate(), command.name(), position, now);

        return languageRepository.save(language);
    }

    public PageResult<Language> listOrderedByPosition(int page, int size) {
        return languageRepository.findAllOrderedByPosition(page, size);
    }
}
