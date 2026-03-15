package com.zanosov.infrastructure.persistence.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface LanguageJpaRepository extends JpaRepository<LanguageRecord, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);

    @Query("SELECT COALESCE(MAX(r.position), 0) FROM LanguageRecord r")
    int findMaxPosition();
}
