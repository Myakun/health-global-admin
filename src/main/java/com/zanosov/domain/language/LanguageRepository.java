package com.zanosov.domain.language;

import com.zanosov.domain.PageResult;

import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository {

    boolean existsByCode(LanguageCode code);

    boolean existsByName(LanguageName name);

    PageResult<Language> findAllOrderedByPosition(int page, int size);

    Optional<Language> findById(UUID id);

    int findMaxPosition();

    Language save(Language language);
}
