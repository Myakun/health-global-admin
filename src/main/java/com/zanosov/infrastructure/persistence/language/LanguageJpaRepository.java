package com.zanosov.infrastructure.persistence.language;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface LanguageJpaRepository extends JpaRepository<LanguageRecord, UUID> {
}
