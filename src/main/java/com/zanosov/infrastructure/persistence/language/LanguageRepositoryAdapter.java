package com.zanosov.infrastructure.persistence.language;

import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
class LanguageRepositoryAdapter implements LanguageRepository {

    private final LanguageJpaRepository jpaRepository;

    LanguageRepositoryAdapter(LanguageJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PageResult<Language> findAllOrderedByPosition(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("position"));
        var result = jpaRepository.findAll(pageable);
        return new PageResult<>(
                result.getContent().stream().map(LanguageRecord::toDomain).toList(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
