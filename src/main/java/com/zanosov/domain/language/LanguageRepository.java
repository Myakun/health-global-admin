package com.zanosov.domain.language;

import com.zanosov.domain.PageResult;

public interface LanguageRepository {

    PageResult<Language> findAllOrderedByPosition(int page, int size);
}
