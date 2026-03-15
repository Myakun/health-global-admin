package com.zanosov.domain.language;

import com.zanosov.domain.PageResult;

public interface LanguageRepository {

    boolean existsByCode(LanguageCode code);

    boolean existsByName(LanguageName name);

    PageResult<Language> findAllOrderedByPosition(int page, int size);

    int findMaxPosition();

    Language save(Language language);
}
