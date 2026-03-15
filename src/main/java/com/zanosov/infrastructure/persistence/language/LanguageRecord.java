package com.zanosov.infrastructure.persistence.language;

import com.zanosov.domain.language.Language;
import com.zanosov.domain.language.LanguageCode;
import com.zanosov.domain.language.LanguageName;
import com.zanosov.infrastructure.persistence.BaseRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class LanguageRecord extends BaseRecord<LanguageRecord> {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private int position;

    public Language toDomain() {
        return new Language(new LanguageCode(code), getCreatedAt(), getId(), new LanguageName(name), position, getUpdatedAt());
    }
}
