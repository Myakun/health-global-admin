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

    public LanguageRecord setCode(String code) {
        this.code = code;
        return this;
    }

    public LanguageRecord setName(String name) {
        this.name = name;
        return this;
    }

    public LanguageRecord setPosition(int position) {
        this.position = position;
        return this;
    }

    public static LanguageRecord fromDomain(Language language) {
        return new LanguageRecord()
                .setId(language.getId())
                .setCode(language.getCode().value())
                .setName(language.getName().value())
                .setPosition(language.getPosition())
                .setCreatedAt(language.getCreatedAt())
                .setUpdatedAt(language.getUpdatedAt());
    }

    public Language toDomain() {
        return new Language(new LanguageCode(code), getCreatedAt(), getId(), new LanguageName(name), position, getUpdatedAt());
    }
}
