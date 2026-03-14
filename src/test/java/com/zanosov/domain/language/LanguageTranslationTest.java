package com.zanosov.domain.language;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LanguageTranslationTest {

    private final Instant now = Instant.now();

    private final UUID id = UUID.randomUUID();

    private final UUID sourceLanguageId = UUID.randomUUID();

    private final UUID translationLanguageId = UUID.randomUUID();

    @Nested
    class Constructor {

        @Test
        void shouldThrowWhenNameIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new LanguageTranslation(now, id, null, sourceLanguageId, translationLanguageId, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("name must not be null");
        }

        @Test
        void shouldThrowWhenSourceLanguageIdIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new LanguageTranslation(now, id, "English", null, translationLanguageId, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("sourceLanguageId must not be null");
        }

        @Test
        void shouldThrowWhenTranslationLanguageIdIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new LanguageTranslation(now, id, "English", sourceLanguageId, null, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("translationLanguageId must not be null");
        }
    }
}
