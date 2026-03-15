CREATE TABLE languages
(
    id         UUID        NOT NULL,
    code       VARCHAR(2)  NOT NULL,
    name       VARCHAR(50) NOT NULL,
    position   INT         NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT pk_languages PRIMARY KEY (id),
    CONSTRAINT uq_languages_code UNIQUE (code),
    CONSTRAINT uq_languages_name UNIQUE (name),
    CONSTRAINT uq_languages_position UNIQUE (position)
);

CREATE TABLE language_translations
(
    id                      UUID         NOT NULL,
    name                    VARCHAR(100) NOT NULL,
    source_language_id      UUID         NOT NULL,
    translation_language_id UUID         NOT NULL,
    created_at              TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at              TIMESTAMPTZ  NOT NULL DEFAULT now(),

    CONSTRAINT pk_language_translations PRIMARY KEY (id),
    CONSTRAINT uq_language_translations UNIQUE (source_language_id, translation_language_id),
    CONSTRAINT fk_language_translations_source_language_id FOREIGN KEY (source_language_id) REFERENCES languages (id),
    CONSTRAINT fk_language_translations_translation_language_id FOREIGN KEY (translation_language_id) REFERENCES languages (id)
);
