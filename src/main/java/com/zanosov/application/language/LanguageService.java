package com.zanosov.application.language;

import com.zanosov.domain.IdGenerator;
import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageCodeAlreadyExistsException;
import com.zanosov.domain.language.LanguageNameAlreadyExistsException;
import com.zanosov.domain.language.LanguageNotFoundException;
import com.zanosov.domain.language.LanguageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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

    public void deleteById(UUID id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id));

        languageRepository.delete(language);
    }

    public Optional<Language> findById(UUID id) {
        return languageRepository.findById(id);
    }

    public PageResult<Language> listOrderedByPosition(int page, int size) {
        return languageRepository.findAllOrderedByPosition(page, size);
    }

    public Language update(UpdateLanguageCommand command) {
        Language language = languageRepository.findById(command.id())
                .orElseThrow(() -> new LanguageNotFoundException(command.id()));

        if (languageRepository.existsByCodeAndIdNot(command.code(), command.id())) {
            throw new LanguageCodeAlreadyExistsException(command.code().value());
        }

        if (languageRepository.existsByNameAndIdNot(command.name(), command.id())) {
            throw new LanguageNameAlreadyExistsException(command.name().value());
        }

        var updated = new Language(command.code(), language.getCreatedAt(), language.getId(), command.name(), language.getPosition(), Instant.now());

        return languageRepository.save(updated);
    }
}
