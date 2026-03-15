package com.zanosov.domain.language;

import com.zanosov.domain.PageResult;

import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository {

    boolean existsByCode(LanguageCode code);

    boolean existsByCodeAndIdNot(LanguageCode code, UUID id);

    boolean existsByName(LanguageName name);

    boolean existsByNameAndIdNot(LanguageName name, UUID id);

    PageResult<Language> findAllOrderedByPosition(int page, int size);

    Optional<Language> findById(UUID id);

    int findMaxPosition();

    Language save(Language language);
}
