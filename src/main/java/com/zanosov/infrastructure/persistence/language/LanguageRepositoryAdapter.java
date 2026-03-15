package com.zanosov.infrastructure.persistence.language;

import com.zanosov.domain.PageResult;
import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageCode;
import com.zanosov.domain.language.LanguageCodeAlreadyExistsException;
import com.zanosov.domain.language.LanguageName;
import com.zanosov.domain.language.LanguageNameAlreadyExistsException;
import com.zanosov.domain.language.LanguageRepository;
import org.springframework.dao.DataIntegrityViolationException;
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
    public boolean existsByCode(LanguageCode code) {
        return jpaRepository.existsByCode(code.value());
    }

    @Override
    public boolean existsByName(LanguageName name) {
        return jpaRepository.existsByName(name.value());
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

    @Override
    public int findMaxPosition() {
        return jpaRepository.findMaxPosition();
    }

    @Override
    public Language save(Language language) {
        try {
            return jpaRepository.save(LanguageRecord.fromDomain(language)).toDomain();
        } catch (DataIntegrityViolationException ex) {
            String cause = ex.getMostSpecificCause().getMessage();

            if (cause.contains("uq_languages_code")) {
                throw new LanguageCodeAlreadyExistsException(language.getCode().value());
            }

            if (cause.contains("uq_languages_name")) {
                throw new LanguageNameAlreadyExistsException(language.getName().value());
            }

            throw ex;
        }
    }
}
